package com.mylexz.utils.text;

import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;

import java.util.ArrayList;
import java.util.List;

public final class TextSpanFormat {

    public static final int UNDERLINE_CODE 		= 1;
    public static final int BOLD_CODE 			= 2;
    public static final int STRIKEOUT_CODE 		= 3;
    public static final int URLSTR_CODE 		= 4;
    public static final int ITALIC_CODE 		= 5;
    public static final int BACKGROUND_CODE 	= 6;
    public static final int FOREGROUND_CODE		= 7;
    public static final int RESIZETXT_CODE 		= 8;
    public static final int SUPERSCRIPT_CODE 	= 9;
    public static final int SUBSCRIPT_CODE 		= 10;
    private static final String[] symbol = {
            "**",			 // BOLD SYMBOL 			0
            "--", 			 // UNDERLINE SYMBOL 		1
            "__", 			 // ITALIC SYMBOL 			2
            "~~", 			 // STRIKEOUT SYMBOL 		3
            "%+", 			 // SUPERSCRIPT SYMBOL 		4
            "%-", 			 // SUBSCRIPT SYMBOL 		5
            "background", 	 // BACKGROUND IDENTIFIER 	6
            "foreground", 	 // FOREGROUND IDENTIFIER 	7
            "link",  		 // Link IDENTIFIER 		8
            "size" 			 // TextSize IDENTIFIER 	9
    };
    public static SpannableString convertStrToSpan(@NonNull String text){
        List<SpanMethod> paramList = new ArrayList<SpanMethod>();
        String originalString = "";
        // for locking
        int bold, italic, underline, url, strikeout, bkgcolor, forcolor, resize, subscript, superscript;
        bold = italic = underline = url = strikeout = bkgcolor = forcolor = resize = superscript = subscript = 0;
        // for current position
        int bold_pos = 0, italic_pos = 0, underline_pos = 0, strikeout_pos = 0, superscript_pos = 0, subscript_pos = 0;
        // for total count
        int count = 0, count_spec = 0;
        for(int x = 0; x < text.length(); x++){
            // for bold symbol
            if(text.charAt(x) == '*' && text.charAt(x+1) == '*'){
                bold++;x++;count++;
                if(bold%2 == 1){
                    paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, BOLD_CODE, 0, null));
                    bold_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(bold_pos).end = ((x + 1) - (count * 2));
                }

                continue;
            }

            // for italic symbol
            else if(text.charAt(x) == '_' && text.charAt(x+1) == '_'){
                italic++;x++;count++;
                if(italic%2 == 1){
                    paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, ITALIC_CODE, 0, null));
                    italic_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(italic_pos).end = ((x + 1) - (count * 2));
                }

                continue;
            }

            // for UNDERLINE SYMBOL
            else if(text.charAt(x) == '-' && text.charAt(x+1) == '-'){
                underline++;x++;count++;
                if(underline%2 == 1){
                    paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, UNDERLINE_CODE, 0, null));
                    underline_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(underline_pos).end = ((x + 1) - (count * 2));
                }

                continue;
            }

            // for STRIKEOUT symbol
            else if(text.charAt(x) == '~' && text.charAt(x+1) == '~'){
                strikeout++;x++;count++;
                if(strikeout%2 == 1){
                    paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, STRIKEOUT_CODE, 0, null));
                    strikeout_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(strikeout_pos).end = ((x + 1) - (count * 2));
                }

                continue;
            }
            // for superscript and subscript symbols
            else if(text.charAt(x) == '%'){
                // for superscript
                if(text.charAt(x+1) == '+'){
                    superscript++;x++;count++;
                    if(superscript%2 == 1){
                        paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, SUPERSCRIPT_CODE, 0, null));
                        superscript_pos = paramList.size() - 1;
                    }
                    else {
                        paramList.get(superscript_pos).end = ((x + 1) - (count * 2));
                    }
                    continue;
                }
                // for subscript
                else if(text.charAt(x+1) == '-'){
                    subscript++;x++;count++;
                    if(subscript%2 == 1){
                        paramList.add(new SpanMethod(((x + 1) - (count * 2)), 0, SUBSCRIPT_CODE, 0, null));
                        subscript_pos = paramList.size() - 1;
                    }
                    else {
                        paramList.get(subscript_pos).end = ((x + 1) - (count * 2));
                    }
                    continue;
                }
                else
                    originalString += '%';
            }

            //for special symbols
            else if(text.charAt(x) == '[' && text.charAt(x+1) == '['){
                x++;
                String op = "";
                // get identifier
                while(text.charAt(++x) != '=' && text.charAt(x) != ']') op += text.charAt(x);
                if(text.charAt(x) == '='){
                    // change TextSize (size)
                    if(op.equalsIgnoreCase(symbol[9])){
                        x++;

                    }
                }
                else if(text.charAt(x) == ']'){

                }
                else originalString += "[[" + op;
            }
			/*
			else if(text.charAt(x) == '$' && text.charAt(x+1) == '$'){
				x++;
				// for URL text
				if(text.charAt(x+1) == 'u'){

				}
				// changing background color
				else if(text.charAt(x+1) == 'b'){

				}
				// changing foreground color
				else if(text.charAt(x+1) == 'f'){

				}
				// changing size of text
				else if(text.charAt(x+1) == 'r'){

				}
				else
					originalString += "$$";
			}*/
            else
                originalString += text.charAt(x);
        }
        SpannableString spanResult = new SpannableString(originalString);
        for(int x = 0; x < paramList.size(); x++){
            switch(paramList.get(x).method){
                case BOLD_CODE:
                    spanResult.setSpan(new StyleSpan(Typeface.BOLD), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case ITALIC_CODE:
                    spanResult.setSpan(new StyleSpan(Typeface.ITALIC), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case UNDERLINE_CODE:
                    spanResult.setSpan(new UnderlineSpan(), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case STRIKEOUT_CODE:
                    spanResult.setSpan(new StrikethroughSpan(), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case SUPERSCRIPT_CODE:
                    spanResult.setSpan(new SuperscriptSpan(), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case SUBSCRIPT_CODE:
                    spanResult.setSpan(new SubscriptSpan(), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
            }
        }
        return spanResult;
    }
    private static class SpanMethod {
        int start;
        int end;
        int method;
        int color;
        String url;
        float size;
        public SpanMethod(int start, int end, int method, int color, @Nullable String url){
            this.start = start;
            this.end = end;
            this.method = method;
            this.color = color;
            this.url = url;

        }
        public SpanMethod(int start, int end, int method, float size){
            this.start = start;
            this.end = end;
            this.method = method;
            this.color = color;
            this.size = size;
        }
    }
}
