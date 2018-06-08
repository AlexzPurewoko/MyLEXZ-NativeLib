package com.mylexz.utils.text;

import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import java.util.ArrayList;
import java.util.List;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import java.util.HashMap;

public final class TextSpanFormat {

    private static final int UNDERLINE_CODE 		= 1;
    private static final int BOLD_CODE 				= 2;
    private static final int STRIKEOUT_CODE 		= 3;
    private static final int URLSTR_CODE 			= 4;
    private static final int ITALIC_CODE 			= 5;
    private static final int BACKGROUND_CODE 		= 6;
    private static final int FOREGROUND_CODE		= 7;
    private static final int RESIZETXT_CODE 		= 8;
    private static final int SUPERSCRIPT_CODE 		= 9;
    private static final int SUBSCRIPT_CODE 		= 10;
	private static final int TYPEFACE_CODE 			= 11;
	private static final int STYLESPAN_CODE 		= 12;
	private static final int TXTALLSPAN_CODE 		= 13;
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
            "size",			 // TextSize IDENTIFIER 	9
			"text" 			 // TextSpecialSpan IDENTIFIER 10
    };
    public static SpannableString convertStrToSpan(@NonNull String text){
        // first initializes for parameter list
		List<SpanMethod> paramList = new ArrayList<SpanMethod>();
        // for the real string without queries
		String originalString = "";
        // for store current position
        int bold_pos = -1, italic_pos = -1, underline_pos = -1, strikeout_pos = -1, superscript_pos = -1, subscript_pos = -1, csize_pos = -1, urltext_pos = -1, bkgcolor_pos = -1, foreground_pos = -1, specspan_pos = -1;
        // for total count
        int count = 0, count_spec = 0;
        for(int x = 0; x < text.length(); x++){
            // for bold symbol
            if(text.charAt(x) == '*' && text.charAt(x+1) == '*'){
                x++;count++;
                if(bold_pos == -1){
					// add new SpanMethod into parameter lists
                    paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, BOLD_CODE, 0, null));
                    // push current location parameter lists
					bold_pos = paramList.size() - 1;
                }
                else {
					// get and modify end field in parameterLists
                    paramList.get(bold_pos).end = (((x + 1) - (count * 2)) - count_spec);
					// passing -1 into bold_pos
					bold_pos = -1;
                }
            }

            // for italic symbol
            else if(text.charAt(x) == '_' && text.charAt(x+1) == '_'){
                x++;count++;
                if(italic_pos == -1){
                    paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, ITALIC_CODE, 0, null));
                    italic_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(italic_pos).end = (((x + 1) - (count * 2)) - count_spec);
					italic_pos = -1;
                }
            }

            // for UNDERLINE SYMBOL
            else if(text.charAt(x) == '-' && text.charAt(x+1) == '-'){
                x++;count++;
                if(italic_pos == -1){
                    paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, UNDERLINE_CODE, 0, null));
                    underline_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(underline_pos).end = (((x + 1) - (count * 2)) - count_spec);
					italic_pos = -1;
                }

                continue;
            }

            // for STRIKEOUT symbol
            else if(text.charAt(x) == '~' && text.charAt(x+1) == '~'){
                x++;count++;
                if(strikeout_pos == -1){
                    paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, STRIKEOUT_CODE, 0, null));
                    strikeout_pos = paramList.size() - 1;
                }
                else {
                    paramList.get(strikeout_pos).end = (((x + 1) - (count * 2)) - count_spec);
					strikeout_pos = -1;
                }
            }
            // for superscript and subscript symbols
            else if(text.charAt(x) == '%'){
                // for superscript
                if(text.charAt(x+1) == '+'){
                    x++;count++;
                    if(superscript_pos == -1){
                        paramList.add(new SpanMethod(((((x + 1) - (count * 2)) - count_spec) - count_spec), 0, SUPERSCRIPT_CODE, 0, null));
                        superscript_pos = paramList.size() - 1;
                    }
                    else {
                        paramList.get(superscript_pos).end = (((x + 1) - (count * 2)) - count_spec);
						superscript_pos = -1;
                    }
                    continue;
                }
                // for subscript
                else if(text.charAt(x+1) == '-'){
                    x++;count++;
                    if(subscript_pos == -1){
                        paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, SUBSCRIPT_CODE, 0, null));
                        subscript_pos = paramList.size() - 1;
                    }
                    else {
                        paramList.get(subscript_pos).end = (((x + 1) - (count * 2)) - count_spec);
						subscript_pos = -1;
                    }
                }
                else
                    originalString += '%';
            }

            //for special symbols
            else if(text.charAt(x) == '[' && text.charAt(x+1) == '['){
                x++;
                String op = "";
                // get identifier
                while(text.charAt(++x) != '=' && text.charAt(x) != ']'){
					if(Character.isWhitespace(text.charAt(x))){
						count_spec++;
						continue;
					}
					op += text.charAt(x);
				}
                if(text.charAt(x) == '='){
                    // change TextSize (size)
                    if(op.equalsIgnoreCase("size") && csize_pos == -1){
                        count_spec += op.length() + 5;
                        op = "";
                        while(text.charAt(++x) != ']'){
							if(Character.isWhitespace(text.charAt(x))){
								count_spec++;
								continue;
							}
							op += text.charAt(x);
						}
                        count_spec += op.length();
                        x++;
                        float size = Float.parseFloat(op);
                        paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, RESIZETXT_CODE, size));
                        csize_pos = paramList.size() - 1;

                    }
                    // link url
                    else if(op.equalsIgnoreCase("link") && urltext_pos == -1){
                        count_spec += op.length() + 5;
                        op = "";
                        while(text.charAt(++x) != ']'){
							if(Character.isWhitespace(text.charAt(x))){
								count_spec++;
								continue;
							}
							op += text.charAt(x);
						}
                        count_spec += op.length();
                        x++;
                        paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, URLSTR_CODE, 0, op));
                        urltext_pos = paramList.size() - 1;
                    }
					
					// background color
					else if((op.equalsIgnoreCase("background") || op.equalsIgnoreCase("bgcolor")) && bkgcolor_pos == -1){
                        count_spec += op.length() + 5;
                        op = "";
                        while(text.charAt(++x) != ']'){
							if(Character.isWhitespace(text.charAt(x))){
								count_spec++;
								continue;
							}
							op += text.charAt(x);
						}
                        count_spec += op.length();
                        x++;
                        paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, BACKGROUND_CODE, getColor(op), null));
                        bkgcolor_pos = paramList.size() - 1;
                    }
					
					// change foreground color
					else if((op.equalsIgnoreCase("foreground") || op.equalsIgnoreCase("color")) && foreground_pos == -1){
                        count_spec += op.length() + 5;
                        op = "";
                        while(text.charAt(++x) != ']'){
							if(Character.isWhitespace(text.charAt(x))){
								count_spec++;
								continue;
							}
							op += text.charAt(x);
						}
                        count_spec += op.length();
                        x++;
                        paramList.add(new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, FOREGROUND_CODE, getColor(op), null));
                        foreground_pos = paramList.size() - 1;
                    }
					else if(op.compareToIgnoreCase("text") > 0 && specspan_pos == -1) {
						count_spec += op.length() + 5;
						
						HashMap<Integer, String> listRes = new HashMap<Integer, String>();
						// put the first argument
						String op1 = op.substring(4, op.length());
						/////////
						op = "";
						SpanMethod res = new SpanMethod((((x + 1) - (count * 2)) - count_spec), 0, TXTALLSPAN_CODE, new HashMap<Integer, String>());
						
					}
                }
                else if(text.charAt(x) == ']'){
                    // change TextSize (size)
                    if(op.equalsIgnoreCase("size") && csize_pos != -1){
                        count_spec += op.length() + 4;
                        x++;
                        paramList.get(csize_pos).end = (((x + 1) - (count * 2)) - count_spec);
                        csize_pos = -1;
                    }
                    // link url
                    else if(op.equalsIgnoreCase("link") && urltext_pos != -1){
                        count_spec += op.length() + 4;
                        x++;
                        paramList.get(urltext_pos).end = (((x + 1) - (count * 2)) - count_spec);
                        urltext_pos = -1;
                    }
					
					// change background color
					else if((op.equalsIgnoreCase("background") || op.equalsIgnoreCase("bgcolor")) && bkgcolor_pos != -1){
                        count_spec += op.length() + 4;
                        x++;
                        paramList.get(bkgcolor_pos).end = (((x + 1) - (count * 2)) - count_spec);
                        bkgcolor_pos = -1;
                    }
					
					// change foreground color
					else if((op.equalsIgnoreCase("foreground") || op.equalsIgnoreCase("color")) && foreground_pos != -1){
                        count_spec += op.length() + 4;
                        x++;
                        paramList.get(foreground_pos).end = (((x + 1) - (count * 2)) - count_spec);
                        foreground_pos = -1;
                    }
                }
                else originalString += "[[" + op;
            }
            else
                originalString += text.charAt(x);
        }
        // check all paramlists
        if(csize_pos != -1) paramList.remove(csize_pos);
        else if(urltext_pos != -1)paramList.remove(urltext_pos);
		else if(bkgcolor_pos != -1) paramList.remove(bkgcolor_pos);
		else if(foreground_pos != -1)paramList.remove(foreground_pos);
		else if(bold_pos != -1)paramList.remove(bold_pos);
		else if(strikeout_pos != -1)paramList.remove(strikeout_pos);
		else if(underline_pos != -1) paramList.remove(underline_pos);
		else if(italic_pos != -1)paramList.remove(italic_pos);
		else if(superscript_pos != -1)paramList.remove(superscript_pos);
		else if(subscript_pos != -1)paramList.remove(subscript_pos);
        //
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
                case RESIZETXT_CODE:
                    spanResult.setSpan(new RelativeSizeSpan(paramList.get(x).size), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
                case URLSTR_CODE:
                    spanResult.setSpan(new URLSpan(paramList.get(x).url), paramList.get(x).start, paramList.get(x).end, 0);
                    break;
				case BACKGROUND_CODE:
					spanResult.setSpan(new BackgroundColorSpan(paramList.get(x).color), paramList.get(x).start, paramList.get(x).end, 0);
					break;
				case FOREGROUND_CODE:
					spanResult.setSpan(new ForegroundColorSpan(paramList.get(x).color), paramList.get(x).start, paramList.get(x).end, 0);
					break;
            }

        }
        return spanResult;
    }
	private static int getTMethod(@NonNull String text){
		if(text.equalsIgnoreCase("bgcolor") || text.equalsIgnoreCase("background")) return BACKGROUND_CODE;
		else if(text.equalsIgnoreCase("foreground") || text.equalsIgnoreCase("color")) return FOREGROUND_CODE;
		else if(text.equalsIgnoreCase("link") || text.equalsIgnoreCase("url")) return URLSTR_CODE;
		else if(text.equalsIgnoreCase("size") || text.equalsIgnoreCase("font-size")) return RESIZETXT_CODE;
		else if(text.equalsIgnoreCase("typeface") || text.equalsIgnoreCase("font-family")) return TYPEFACE_CODE;
		else if(text.equalsIgnoreCase("style") || text.equalsIgnoreCase("font-style")) return STYLESPAN_CODE;
		return -1;
	}
	private static int getColor(@NonNull String color){
		int clr = 0 ;
		if(color.charAt(0) == '#'){
			clr = Color.parseColor(color);
		}
		else {
			if(color.equalsIgnoreCase("black")) clr = Color.BLACK;
			else if(color.equalsIgnoreCase("blue")) clr = Color.BLUE;
			else if(color.equalsIgnoreCase("cyan")) clr = Color.CYAN;
			else if(color.equalsIgnoreCase("dkgray")) clr = Color.DKGRAY;
			else if(color.equalsIgnoreCase("gray")) clr = Color.GRAY;
			else if(color.equalsIgnoreCase("green")) clr = Color.GREEN;
			else if(color.equalsIgnoreCase("ltgray")) clr = Color.LTGRAY;
			else if(color.equalsIgnoreCase("magenta")) clr = Color.MAGENTA;
			else if(color.equalsIgnoreCase("red")) clr = Color.RED;
			else if(color.equalsIgnoreCase("transparent")) clr = Color.TRANSPARENT;
			else if(color.equalsIgnoreCase("white")) clr = Color.WHITE;
			else if(color.equalsIgnoreCase("yellow")) clr = Color.YELLOW;
			else clr = Color.BLACK;
			
		}
		return clr;
	}
    public static class SpanMethod {
        int start;
        int end;
        int method;
        int color;
        String url;
        float size;
		
		// for TXTALLSPAN_CODE (special)
		HashMap<Integer, String> parameters;
		public SpanMethod(int start, int end, int method, @NonNull HashMap<Integer, String> parameters){
			this.start = start;
			this.end = end;
			this.method = method;
			this.parameters = parameters;
		}
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
            this.size = size;
        }
    }
}
