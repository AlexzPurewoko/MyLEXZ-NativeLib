/*
 * Copyright (C) 2018 by Alexzander Purwoko Widiantoro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
 
 /*
  * BasicScrollLinearLayout.java [[ALPHA VERSIONS!]]
  * 
  * Creates an auto views layout with files both from assets and filepath
  * View Widgets that are supported is TextView, ImageView, SoundBasicMediaPlayer
  * 
  * Usages Examples:
  * BasicScrollLinearLayout layout = new BasicScrollLinearLayout(this);
  * layout.inflateFromAssets("layout.lyt");
  * RootView.addView(layout.getRootView());
  * 
  * // The "layout.lyt" in the assets (list configuration)
  * 
  *		 <[TextView]
  *			width="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 		height="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 		isSpannable="[true | false]"
  * 		gravity="[top | bottom | left | right | center | center_vertical | center_horizontal | fill | fill_vertical | fill_horizontal | clip_vertical | clip_horizontal | start | end]" >
  *		 This is a text!
  *		 <[TextView]>
  * 	 <[ImageView]
  * 		width="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 		height="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 		gravity="[top | bottom | left | right | center | center_vertical | center_horizontal | fill | fill_vertical | fill_horizontal | clip_vertical | clip_horizontal | start | end]" >
  * 		src="[From Drawable Resources(starts with $) | from Assets(starts with @) | From a file(starts with /)]" />
  * 	<[MusicPlayer]
  * 		music="[From Drawable Resources(starts with $) | from Assets(starts with @) | From a file(starts with /)]"
  * 		musicName="[The name of the music played]" />
  *
  *
  *
  * Guidelines :
  * 	1.  TextView
  * 		Shows the TextView
  * 		Attributes :
  * 			1). width="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 				Sets the width of views.
  *					The value can be MATCH_PARENT or WRAP_CONTENT or Fixed Size or Resources Dimension
  *					You must select one value, if you select two or much value, the app will crash or returned an exception!
  *					
  *					The value of Fixed Size is the number of unit followed by type unit(dp, dip, dpx, dpy, pt, mm, cm, px) 
  *					Example : 12dp, 5cm, 6mm 
  *					
  *					The value of Resources Dimension starts with '$' and followed by Classpath into resources dimension
  *					Example : $android.R.dimen.thumbnail_width, R.dimen.lay_width
  *
  * 			2). height="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 				Sets the height of views.
  *					The value can be MATCH_PARENT or WRAP_CONTENT or Fixed Size or Resources Dimension
  *					You must select one value, if you select two or much value, the app will crash or returned an exception!
  *					
  *					The value of Fixed Size is the number of unit followed by type unit(dp, dip, dpx, dpy, pt, mm, cm, px) 
  *					Example : 12dp, 5cm, 6mm 
  *					
  *					The value of Resources Dimension starts with '$' and followed by Classpath into resources dimension
  *					Example : $android.R.dimen.thumbnail_height, R.dimen.lay_height
  *
  *
  * 			3). isSpannable="[true | false]"
  * 				Sets the text is spannable or not
  * 				true if spannable and false if not.
  * 				The spannable documentation can be found on {@link com.mylexz.utils.text.TextSpanFormat} because we sets the formatted text with instance of them {@link TextSpanFormat}
  *
  * 			4). gravity="[top | bottom | left | right | center | center_vertical | center_horizontal | fill | fill_vertical | fill_horizontal | clip_vertical | clip_horizontal | start | end]"
  * 				Sets the text alignment/position layout
  * 				You can sets multiple values separated with pipe '|'
  * 				Example : top | left
  *
  * 	2.  ImageView
  * 		Shows the ImageView
  * 		Attributes :
  * 			1). width="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 				Sets the width of views.
  *					The value can be MATCH_PARENT or WRAP_CONTENT or Fixed Size or Resources Dimension
  *					You must select one value, if you select two or much value, the app will crash or returned an exception!
  *					
  *					The value of Fixed Size is the number of unit followed by type unit(dp, dip, dpx, dpy, pt, mm, cm, px) 
  *					Example : 12dp, 5cm, 6mm 
  *					
  *					The value of Resources Dimension starts with '$' and followed by Classpath into resources dimension
  *					Example : $android.R.dimen.thumbnail_width, R.dimen.lay_width
  *
  * 			2). height="[MATCH_PARENT | WRAP_CONTENT | Fixed Size | Resources Dimension]"
  * 				Sets the height of views.
  *					The value can be MATCH_PARENT or WRAP_CONTENT or Fixed Size or Resources Dimension
  *					You must select one value, if you select two or much value, the app will crash or returned an exception!
  *					
  *					The value of Fixed Size is the number of unit followed by type unit(dp, dip, dpx, dpy, pt, mm, cm, px) 
  *					Example : 12dp, 5cm, 6mm 
  *					
  *					The value of Resources Dimension starts with '$' and followed by Classpath into resources dimension
  *					Example : $android.R.dimen.thumbnail_height, R.dimen.lay_height
  *
  * 			3). gravity="[top | bottom | left | right | center | center_vertical | center_horizontal | fill | fill_vertical | fill_horizontal | clip_vertical | clip_horizontal | start | end]"
  * 				Sets the text alignment/position layout
  * 				You can sets multiple values separated with pipe '|'
  * 				Example : top | left
  *
  * 			4). src="[From Drawable Resources(starts with $) | from Assets(starts with @) | From a file(starts with /)]"
  * 				Sets the image source to show.
  * 				You can sets the image source by following method:
  * 					1). from drawable resources
  * 						Shows an image that found in Drawable Resources
  * 						Starts with dollars ('$') and following the Classpath resources (R class)
  * 						Example : $R.drawable.ic_launcher, $R.mipmap.ic_launcher
  * 						Warning! : if you sets the wrong value(if the resources not found) your app will crash!
  *
  * 					2). from Assets
  * 						Shows an image that found in Assets Directory
  * 						Starts with aèt ('@') and following the fullpath into file
  * 						Example : @src/image_launcher.jpg, @image.jpg
  * 						Warning! : if you sets the wrong value(if the file is not found) your app will crash or returned an exception!
  *
  * 					3). from File Directory
  * 						Shows an image that found in File Directory
  * 						Starts with slash ('/') indicates the full patg into file . 
  * 						Example : /sdcard/Documents/photo.jpg, /sdcard/DCIM/Camera/IMG_XXX.jpg
  * 						Warning! : if you sets the wrong value(if the image file not found) your app will crash or returned an exception!
  *
  *
  * 	3.  MusicPlayer
  * 		Play the selected music from "music" attributes.
  * 		Attributes :
  * 			1). music="[From Drawable Resources(starts with $) | from Assets(starts with @) | From a file(starts with /)]"
  * 				Sets the music location to play, This is the important Attributes!
  * 				You can sets the music sources by the following methods :
  * 					a.  from the raw Resources
  * 						Play a music from raw resources
  * 						Starts with dollars ('$') and following the Classpath resources (R class)
  * 						Example : $R.raw.music
  * 						Warning! : if you sets the wrong value(if the resources not found) your music couldn't play or app crash!
  * 					
  * 					b.  from the assets
  * 						Play a music from assets
  * 						Starts with aèt ('@') and following the fullpath to music file
  * 						Example : @sound/backsound.mp3, @sound.mp3
  * 						Warning! : if you sets the wrong value(if the the file not found) your music couldn't play or app crash!
  * 					c.  from the File 
  * 						Play a music from File 
  * 						Starts with slash ('/') indicates the full filepath
  * 						Example : /sdcard/Music/backsound.mp3
  * 						Warning! : if you sets the wrong value(if the file not found) your music couldn't play or app crash or returned an exception!
  * 			2). musicName="[music name]" [Optional Attributes]
  * 				Sets the current name of music that played
  */
package com.mylexz.utils.layout;


import android.widget.ScrollView;
import android.widget.LinearLayout;
import java.util.List;
import android.view.View;
import android.content.Context;
import android.support.annotation.NonNull;
import com.mylexz.utils.R;
import java.io.IOException;
import android.content.res.AssetManager;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.reflect.Field;
import android.view.Gravity;
import android.support.annotation.Nullable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;
import com.mylexz.utils.text.TextSpanFormat;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.mylexz.utils.music.SoundMediaBasicPlayer;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import static com.mylexz.utils.Conversion.toPixels;
public class BasicScrollLinearLayout
{
	ScrollView mRootView;
	LinearLayout mParentView;
	List<ViewElements> mAllChildViewElements;
	Context mContext;
	/*
	 * Constructor() -> {@link BasicScrollLinearLayout}
	 * @param mContext the current activity [NonNull]
	 */
	public BasicScrollLinearLayout(@NonNull Context mContext){
		this.mContext = mContext;
		mRootView = (ScrollView) View.inflate(mContext, R.layout.act_vertical_scrollview, null);
		mParentView = (LinearLayout) View.inflate(mContext, R.layout.act_vertical_linear, null);
		mRootView.addView(mParentView);
	}
	/*
	 * inflates the parsed layout file in the asset
	 * @param assetPath The path to the file in assets [NonNull]
	 * @throws IOException if i/o errors(ex: File not found, etc)
	 * @throws Exception if any exception
	 * @noreturn 
	 */
	public void inflateFromAssets(@NonNull String assetPath) throws IOException, Exception{
		// Get the File and place into inputStream
		AssetManager mAssetManager = mContext.getAssets();
		InputStream mCurrentFileAssetStream = mAssetManager.open(assetPath);
		// parse from a file
		List<ViewListed> mListWidgets = getDeclaredViewFromStream(mCurrentFileAssetStream);
		buildWidget(mListWidgets);
		parseWidgets();
		mCurrentFileAssetStream.close();
	}
	/*
	 * inflates the parsed layout file on File
	 * @param filePath The fullpath to the file [NonNull]
	 * @throws IOException if i/o errors(ex: File not found, etc)
	 * @throws Exception if any exception
	 * @noreturn 
	 */
	public void inflateFromFile(@NonNull String filePath) throws FileNotFoundException, IOException, Exception{
		InputStream mCurrentFileStream = new FileInputStream(filePath);
		// parse from a file
		List<ViewListed> mListWidgets = getDeclaredViewFromStream(mCurrentFileStream);
		buildWidget(mListWidgets);
		parseWidgets();
		mCurrentFileStream.close();
	}
	/*
	 * Gets the root View
	 * @return mRootView the rootView layout [ScrollView]
	 */
	public View getRootView(){
		return mRootView;
	}
	/*
	 * Gets the parent view
	 * @return mParentView the ParentView [LinearLayout]
	 */
	public View getParentView(){
		return mParentView;
	}
	/*
	 * Gets The List of Child Elements
	 * @return mAllChildViewElements The List of Elements {@link List<ViewElements>}
	 */
	public List<ViewElements> getAllChildWidgets(){
		return mAllChildViewElements;
	}
	/*
	 * Gets the View Elements by index
	 * @return null if the index is out of range size
	 * @return {@link ViewElements} the view elements
	 */
	public ViewElements getChildWidgets(int index){
		return (index < mAllChildViewElements.size())? mAllChildViewElements.get(index):null;
	}
	private void parseWidgets()
	{
		// TODO: Implement this method
		for(int x = 0; x < mAllChildViewElements.size(); x++){
			int elementType = mAllChildViewElements.get(x).elementType;
			if(elementType != ViewElements.ELEMENT_TYPE_MUSICWIDGET)
				mParentView.addView((View)mAllChildViewElements.get(x).element);
			else 
				mParentView.addView((View)((SoundMediaBasicPlayer)mAllChildViewElements.get(x).element).getLayout());
		}
	}
	private void buildWidget(List<BasicScrollLinearLayout.ViewListed> mListWidgets) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException, IllegalStateException, Resources.NotFoundException
	{
		// TODO: Implement this method
		mAllChildViewElements = new ArrayList<ViewElements>();
		for(int x = 0; x < mListWidgets.size(); x++){
			ViewListed mParsedView = mListWidgets.get(x);
			String mViewType = mParsedView.typeOfView;
			ViewElements mViewElements = null;
			if(mViewType.equalsIgnoreCase("TextView") || mViewType.equalsIgnoreCase("text"))mViewElements = buildTextView(mParsedView);
			else if(mViewType.equalsIgnoreCase("ImageView") || mViewType.equalsIgnoreCase("image"))mViewElements = buildImageView(mParsedView);
			else if(mViewType.equalsIgnoreCase("SoundMediaBasicPlayer") || mViewType.equalsIgnoreCase("MusicPlayer") || mViewType.equalsIgnoreCase("music"))mViewElements = buildMusicPlayer(mParsedView);
			if(mViewElements != null)
				mAllChildViewElements.add(mViewElements);
		}
	}
	
	private BasicScrollLinearLayout.ViewElements buildMusicPlayer(BasicScrollLinearLayout.ViewListed mParsedView) throws IOException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException, IllegalStateException, NoSuchFieldException
	{
		// TODO: Implement this method
		HashMap<String, String> mSoundParams = mParsedView.listParams;
		String mTextStored = mParsedView.storedText;
		if(mSoundParams.containsKey("music")){
			String soundName = null;
			if(mSoundParams.containsKey("musicName"))soundName = mSoundParams.get("musicName");
			String musicPath = mSoundParams.get("music");
			SoundMediaBasicPlayer sPlayer = new SoundMediaBasicPlayer(mContext, null);
			if(musicPath.charAt(0) == '@'){
				sPlayer.setMusic(musicPath.substring(1, musicPath.length()), soundName);
			}
			else if(musicPath.charAt(0) == '$'){
				String valPath = musicPath.substring(1, musicPath.length());
				String[] mList = splitStrings(valPath, '.');
				int resId = getIdentifierResources(mList);
				sPlayer.setMusic(resId, soundName);
			}
			else if(musicPath.charAt(0) == '/'){
				sPlayer.setMusicFromFile(musicPath, soundName);
			}
			ViewElements mSoundViewPlayerElements = new ViewElements(ViewElements.ELEMENT_TYPE_MUSICWIDGET, sPlayer);
			return mSoundViewPlayerElements;
		}
		else
			return null;
	}
	private BasicScrollLinearLayout.ViewElements buildImageView(BasicScrollLinearLayout.ViewListed mParsedView) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, ClassNotFoundException, Resources.NotFoundException
	{
		// TODO: Implement this method
		HashMap<String, String> mImageViewParams = mParsedView.listParams;
		String mTextStored = mParsedView.storedText;

		int height, width, gravity;
		String src;
		Drawable mDrawable = null;
		// sets to default value
		height = LinearLayout.LayoutParams.WRAP_CONTENT;
		width = LinearLayout.LayoutParams.WRAP_CONTENT;
		gravity = Gravity.CENTER;
		
		if(mImageViewParams.containsKey("height")){
			String value = mImageViewParams.get("height");
			// fixed value
			if(!Character.isLetter(value.charAt(0))){
				float val = toPixels(mContext, value);
				height = Math.round(val);
			}
			else if(value.charAt(0) == '$'){
				String[] vals = splitStrings(value.substring(1, value.length()), '.');
				height = Math.round( mContext.getResources().getDimension(getIdentifierResources(vals)));
			}
			else if(value.equalsIgnoreCase("thumbnail_height")){
				float val = mContext.getResources().getDimension(android.R.dimen.thumbnail_height);
				height = (int) val;
			}
			else {
				Field mField = LinearLayout.LayoutParams.class.getField(value.toUpperCase());
				height = mField.getInt(null);
			}
		}
		if(mImageViewParams.containsKey("width")){
			String value = mImageViewParams.get("width");
			if(!Character.isLetter(value.charAt(0))){
				float val = toPixels(mContext, value);
				width = Math.round(val);
			}
			else if(value.charAt(0) == '$'){
				String[] vals = splitStrings(value.substring(1, value.length()), '.');
				width = Math.round( mContext.getResources().getDimension(getIdentifierResources(vals)));
			}
			else if(value.equalsIgnoreCase("thumbnail_width")){
				float val = mContext.getResources().getDimension(android.R.dimen.thumbnail_width);
				width = (int) val;
			}
			else {
				Field mField = LinearLayout.LayoutParams.class.getField(value.toUpperCase());
				width = mField.getInt(null);
			}
		}
		if(mImageViewParams.containsKey("src")){
			String value = mImageViewParams.get("src");
			src = value;
			// from asset stream
			if(src.charAt(0) == '@'){
				String valImage = src.substring(1, src.length());
				InputStream imageStream = mContext.getAssets().open(valImage);
				String[] fn = splitStrings(valImage, '/');
				String filename = fn[fn.length - 1];
				mDrawable = Drawable.createFromStream(imageStream, filename);
			}
			else if(src.charAt(0) == '$'){
				String valPath = src.substring(1, src.length());
				String[] mList = splitStrings(valPath, '.');
				int resId = getIdentifierResources(mList);
				mDrawable = mContext.getResources().getDrawable(resId);
			}
			else if(src.charAt(0) == '/'){
				mDrawable = Drawable.createFromPath(src);
			}
		}
		if(mImageViewParams.containsKey("gravity")){
			String value = mImageViewParams.get("gravity");
			gravity = getGravity(value);
		}
		ImageView mImageWidget = new ImageView(mContext);
		LinearLayout.LayoutParams mImLay = new LinearLayout.LayoutParams(width, height, gravity);
		mImLay.gravity = gravity;
		mImageWidget.setLayoutParams(mImLay);
		mImageWidget.setImageDrawable(mDrawable);
		ViewElements mImageViewElements = new ViewElements(ViewElements.ELEMENT_TYPE_IMAGEVIEW, mImageWidget);
		return mImageViewElements;
	}
	private BasicScrollLinearLayout.ViewElements buildTextView(BasicScrollLinearLayout.ViewListed mParsedView) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
	{
		// TODO: Implement this method
		HashMap<String, String> mTextViewParams = mParsedView.listParams;
		String mTextStored = mParsedView.storedText;
		
		int height, width, gravity;
		boolean isSpannable;
		// sets to default value
		height = LinearLayout.LayoutParams.WRAP_CONTENT;
		width = LinearLayout.LayoutParams.MATCH_PARENT;
		gravity = getGravity("CENTER_VERTICAL | CENTER_HORIZONTAL");
		isSpannable = false;
		if(mTextViewParams.containsKey("height")){
			String value = mTextViewParams.get("height");
			if(!Character.isLetter(value.charAt(0))){
				float val = toPixels(mContext, value);
				height = Math.round(val);
			}
			else if(value.charAt(0) == '$'){
				String[] vals = splitStrings(value.substring(1, value.length()), '.');
				height = Math.round( mContext.getResources().getDimension(getIdentifierResources(vals)));
			}
			else {
				Field mField = LinearLayout.LayoutParams.class.getField(value.toUpperCase());
				height = mField.getInt(null);
			}
		}
		if(mTextViewParams.containsKey("width")){
			String value = mTextViewParams.get("width");
			if(!Character.isLetter(value.charAt(0))){
				float val = toPixels(mContext, value);
				width = Math.round(val);
			}
			else if(value.charAt(0) == '$'){
				String[] vals = splitStrings(value.substring(1, value.length()), '.');
				width = Math.round( mContext.getResources().getDimension(getIdentifierResources(vals)));
			}
			else {
				Field mField = LinearLayout.LayoutParams.class.getField(value.toUpperCase());
				width = mField.getInt(null);
			}
		}
		if(mTextViewParams.containsKey("isSpannable")){
			String value = mTextViewParams.get("isSpannable");
			isSpannable = Boolean.parseBoolean(value);
		}
		if(mTextViewParams.containsKey("gravity")){
			String value = mTextViewParams.get("gravity");
			gravity = getGravity(value);
		}
		TextView mTextWidget = new TextView(mContext);
		mTextWidget.setLayoutParams(new LinearLayout.LayoutParams(width, height));
		if(isSpannable){
			mTextWidget.setMovementMethod(LinkMovementMethod.getInstance());
			mTextWidget.setText(TextSpanFormat.convertStrToSpan(mContext, mTextStored, 0));
		}
		else
			mTextWidget.setText(mTextStored);
		mTextWidget.setGravity(gravity);
		ViewElements mTextViewElements = new ViewElements(ViewElements.ELEMENT_TYPE_TEXTVIEW, mTextWidget);
		mTextViewElements.isSpannable = isSpannable;
		return mTextViewElements;
	}

	private String[] splitStrings(String result, char regexSplit){
		List<String> mList = new ArrayList<String>();
		String tmp = "";
		int x = 0;
		while( x < result.length()){
			if(result.charAt(x) == regexSplit){
				mList.add(tmp);
				tmp = "";
			}
			else tmp += result.charAt(x);
			x++;
		}
		if(!tmp.isEmpty())mList.add(tmp);
		String[] toArr = new String[mList.size()];
		for(x = 0; x < mList.size(); x++) toArr[x] = mList.get(x);
		return toArr;
	}
	private int getIdentifierResources(String[] resClass){
		String contentName = resClass[resClass.length - 1];
		String resIdentifierName = resClass[resClass.length - 2];
		int rPos = resClass.length - 3;
		String mPackageName;
		if(rPos == 0) mPackageName = mContext.getPackageName();
		else {
			mPackageName = "";
			for(int x = 0; x < rPos; x++){
				mPackageName+=resClass[x];
				if((x+1)!= rPos)mPackageName+='.';
			}
		}
		Resources res = mContext.getResources();
		int result = res.getIdentifier(contentName, resIdentifierName, mPackageName);
		return result;
	}
	private int getIntValueField(String classpath, String fieldName) throws ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Field mField = getField(classpath, fieldName);
		return mField.getInt(null);
	}
	private Field getField(String classpath, String fieldName) throws ClassNotFoundException, NoSuchFieldException{
		return getClassFromStr(classpath).getField(fieldName);
	}
	private Class<?> getClassFromStr(String classpath) throws ClassNotFoundException{
		return Class.forName(classpath);
	}
	private List<ViewListed> getDeclaredViewFromStream(InputStream mCurrentFileAssetStream) throws Exception, IOException{
		int read;
		int curr_pos = 0;
		List<ViewListed> mListWidgets = new ArrayList<ViewListed>();
		while((read = mCurrentFileAssetStream.read()) != -1){
			if(Character.isWhitespace(read))continue;
			if(read == '\n')continue;
			if(read == '\t')continue;
			if(read == '<' && ((read = mCurrentFileAssetStream.read()) == '[')){
				// Get Name of views 
				String widgetName = "";
				while((read = mCurrentFileAssetStream.read()) != ']')widgetName+=((char)read);
				//read = mCurrentFileAssetStream.read();
				if(read == '>');// the end
				// skip newlines, tabs, and spaces
				while(!Character.isLetterOrDigit((char)(read = mCurrentFileAssetStream.read())));
				// getting properties
				HashMap<String, String> params = new HashMap<String, String>();
				while(read != '/' && read != '>'){
					String propName = "", itsValue = "";
					while(read != '='){
						propName+=((char)read);
						read = mCurrentFileAssetStream.read();
					}
					// skipping "
					read = mCurrentFileAssetStream.read();
					while((read = mCurrentFileAssetStream.read()) != '"')itsValue+=((char)read);
					params.put(propName, itsValue);
					// skipping any whitespace, linebreaks and tabs and then go into next parameters
					//while(!Character.isLetterOrDigit((char)(read = mCurrentFileAssetStream.read())));
					while(true){
						read = mCurrentFileAssetStream.read();
						if(read != '\t' && read != '\n' && read != ' ')break;
					}
					//read = mCurrentFileAssetStream.read();
				}
				mListWidgets.add(new ViewListed(params, widgetName, null));
				curr_pos = mListWidgets.size() - 1;
				if(read == '/'){read = mCurrentFileAssetStream.read();continue;}
				// skipping >
				read = mCurrentFileAssetStream.read();
				// get the text until the closing
				String mContentString = "";
				while(true){
					if(read == '<'){
						read = mCurrentFileAssetStream.read();
						if(read == '['){
							String closer = "";
							while((read = mCurrentFileAssetStream.read()) != ']')closer+=((char)read);
							if(!closer.equalsIgnoreCase(widgetName))throw new Exception("Closing tags <["+closer+"]> doesn't match with <["+widgetName+"]>");
							// skipping >
							read = mCurrentFileAssetStream.read();
							break;
						}
						else mContentString += "<[";
					}
					else mContentString += ((char)read);
					read = mCurrentFileAssetStream.read();
				}
				mListWidgets.get(curr_pos).storedText = mContentString;

			}
		}
		return mListWidgets;
	}


	private int getGravity(String val)
	{
		// TODO: Implement this method
		List<String> mListgravity = new ArrayList<String>();
		// splitting
		String tmp = "";
		int gravity = 0;
		for(int x = 0; x < val.length(); x++){
			if(Character.isWhitespace(val.charAt(x)))continue;
			if(val.charAt(x) == '|'){
				// make as UPPERCASE LETTER
				mListgravity.add(tmp.toUpperCase());
				tmp = "";
				continue;
			}
			tmp+=val.charAt(x);
		}
		if(tmp!=null) mListgravity.add(tmp.toUpperCase());
		// getting a field and pass into gravity
		for(int x = 0; x < mListgravity.size(); x++){
			try
			{
				Field mField = Gravity.class.getField(mListgravity.get(x));
				gravity |= mField.getInt(null);
			}
			catch (IllegalAccessException e){}
			catch (NoSuchFieldException e)
			{}
		}
		return gravity;
	}
	
	/*
	 * ViewElements
	 * Store the element views 
	 */
	public static class ViewElements {
		public static final int ELEMENT_TYPE_TEXTVIEW = 0x0006a;
		public static final int ELEMENT_TYPE_IMAGEVIEW = 0x0006b;
		public static final int ELEMENT_TYPE_MUSICWIDGET = 0x0006c;
		// the element type identifier, value is declared above
		public int elementType;
		// the view elements
		public Object element;
		// for text formatting, isSpannable?
		public boolean isSpannable;
		public ViewElements(int elementType, Object element){
			this.elementType = elementType;
			this.element = element;
		}
	}
	private static class ViewListed {
		HashMap<String, String> listParams;
		String typeOfView; // {@link ViewElements int public static final}
		String storedText;
		public ViewListed(@NonNull HashMap<String, String> listParams, @NonNull String typeOfView, @Nullable String storedText){
			this.storedText = storedText;
			this.listParams = listParams;
			this.typeOfView = typeOfView;
		}
	}
}
