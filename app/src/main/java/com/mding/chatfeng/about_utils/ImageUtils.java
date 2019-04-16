package com.mding.chatfeng.about_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * 图片转换工具
 * @author zll
 *
 */
public class ImageUtils {
	public static String saveDir = Environment.getExternalStorageDirectory().getPath() + "/chat_image";
	public static File saveBitmapToFile(File file, String newpath) {
		try {

			// BitmapFactory options to downsize the image
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			o.inSampleSize = 6;
			// factor of downsizing the image

			FileInputStream inputStream = new FileInputStream(file);
			//Bitmap selectedBitmap = null;
			BitmapFactory.decodeStream(inputStream, null, o);
			inputStream.close();

			// The new size we want to scale to
			final int REQUIRED_SIZE = 75;

			// Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
					o.outHeight / scale / 2 >= REQUIRED_SIZE) {
				scale *= 2;
			}

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			inputStream = new FileInputStream(file);

			Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
			inputStream.close();

			File aa = new File(newpath);
			aa.delete();
			if (!aa.exists()) {
				aa.mkdirs();
				aa = new File(newpath);
			}
			return aa;
		} catch (Exception e) {
			return null;
		}
	}
	public static String GetStringByImageView(Bitmap bitmap){
		Bitmap bitmap1 =comp(bitmap);
//		Bitmap bitmap1 = getBitmapCompress(imgPath);
		// 从ImageView得到Bitmap对象
//        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
		// 把Bitmap转码成字符串
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap1.compress(Bitmap.CompressFormat.PNG, 100,baos);
		String imageBase64 = new String (Base64.encode(baos.toByteArray(), 0));
		Log.e("imageBase64","data:image/png;base64,"+imageBase64);
		return "data:image/png;base64,"+imageBase64;
	}
	/**
	 * 将图片转换成Base64编码的字符串
	 */
	public static String imageToBase64(String path){
		if(TextUtils.isEmpty(path)){
			return null;
		}
		InputStream is = null;
		byte[] data = null;
		String result = null;
		try{
			is = new FileInputStream(path);
			//创建一个字符流大小的数组。
			data = new byte[is.available()];
			//写入数组
			is.read(data);
			//用默认的编码格式进行编码
			result = Base64.encodeToString(data,Base64.DEFAULT);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(null !=is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/**
	 * 通过Base32将Bitmap转换成Base64字符串
	 * @param bit
	 * @return
	 */
	public static String Bitmap2StrByBase64(Bitmap bit){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
		byte[] bytes=bos.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
//		return "data:image/png;base64,"+ Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	public static void useBase64(Context context, ImageView imageView, String s) {
		try {
			byte[] decodedByte = Base64.decode(s, Base64.DEFAULT);
			Glide.with(context).load(decodedByte)
                    .dontAnimate()
                    .bitmapTransform(new CropCircleTransformation(context))
                    .placeholder(imageView.getDrawable())
                    .into(imageView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void useBase64WithError(Context context, ImageView imageView, String s, int errorDrawable) {
		byte[] decodedByte = Base64.decode(s, Base64.DEFAULT);
		Glide.with(context).load(decodedByte)
				.dontAnimate()
				.error(errorDrawable)
				.bitmapTransform(new CropCircleTransformation(context))
				.placeholder(imageView.getDrawable())
				.into(imageView);
	}
	private static String generateFileName() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 保存bitmap到本地
	 *
	 * @param context
	 * @param mBitmap
	 * @return
	 */
	public static File saveBitmap(Context context, Bitmap mBitmap) {
		String savePath;
		File filePic;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			savePath = saveDir;
		} else {
			savePath = context.getApplicationContext().getFilesDir()
					.getAbsolutePath()
					+ saveDir;
		}
		try {
			filePic = new File(savePath + generateFileName() + ".jpg");
			if (!filePic.exists()) {
				filePic.getParentFile().mkdirs();
				filePic.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(filePic);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return filePic;
	}

	/**
	 * 保存文件
	 * @param bm
	 * @throws IOException
	 */
	public static File saveFile(Bitmap bm) throws IOException {
		String path = Environment.getExternalStorageDirectory().getPath() +"/chatHead/";
		String fileName = SplitWeb.getUserId()+ ".jpg";
		File dirFile = new File(path);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		File myCaptureFile = new File(path + fileName);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
		bos.flush();
		bos.close();
		return myCaptureFile;
	}
	/**
	 * 将二进制流转换成图片（Bitmap）
	 * @param temp
	 * @return
	 */
	public static Bitmap getBitmapFromByte(Context context, byte[] temp){
		if(temp != null){
			Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
			saveBitmap(context,bitmap);
			return bitmap;
		}else{
			return null;
		}
	}

	//	 public static String post(String url, Map<String, String> params, Map<String, File> files)throws IOException {
	public static String post(String url, Map<String, File> files)throws IOException {
		String BOUNDARY = UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";


		URL uri = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(10 * 1000); // 缓存的最长时间
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);


		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
//	        for (Map.Entry<String, String> entry : params.entrySet()) {
//	            sb.append(PREFIX);
//	            sb.append(BOUNDARY);
//	            sb.append(LINEND);
//	            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
//	            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
//	            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
//	            sb.append(LINEND);
//	            sb.append(entry.getValue());
//	            sb.append(LINEND);
//	        }

		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(sb.toString().getBytes());
		// 发送文件数据
		if (files != null)
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ file.getValue().getName() + "\"" + LINEND);
				sb1.append("Content-Type: application/x-jpg; charset=" + CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());


				InputStream is = new FileInputStream(file.getValue());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}


				is.close();
				outStream.write(LINEND.getBytes());
			}


		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		// 得到响应码
		int res = conn.getResponseCode();
		InputStream in = conn.getInputStream();
		StringBuilder sb2 = new StringBuilder();
		if (res == 200) {
			int ch;
			while ((ch = in.read()) != -1) {
				sb2.append((char) ch);
			}
		}
		outStream.close();
		conn.disconnect();
		return sb2.toString();
	}


	public static final float DISPLAY_WIDTH = 200;
	public static final float DISPLAY_HEIGHT = 200;

	public static Bitmap decodeBitmap(String path) {
		BitmapFactory.Options op = new BitmapFactory.Options();
		//inJustDecodeBounds
		//If set to true, the decoder will return null (no bitmap), but the out…
		op.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(path, op); //获取尺寸信息
		//获取比例大小
		int wRatio = (int) Math.ceil(op.outWidth / DISPLAY_WIDTH);
		int hRatio = (int) Math.ceil(op.outHeight / DISPLAY_HEIGHT);
		//如果超出指定大小，则缩小相应的比例
		if (wRatio > 1 && hRatio > 1) {
			if (wRatio > hRatio) {
				op.inSampleSize = wRatio;
			} else {
				op.inSampleSize = hRatio;
			}
		}
		op.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(path, op);
		return bmp;
	}

	public static Bitmap cropBitmap(String path, Bitmap bitmap, int maxW, int maxH) {

		if (path != null && bitmap != null) {
			return null;
		}
		if (path == null && bitmap == null) {
			return null;
		}
		if (maxW == 0 || maxH == 0) {
			return null;
		}
		Bitmap bm;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		if (path != null) {
			bm = BitmapFactory.decodeFile(path, options);
		}
		options.inJustDecodeBounds = false;
		int width = options.outWidth;
		int height = options.outHeight;

		if (width <= maxW && height <= maxH) {
			return BitmapFactory.decodeFile(path);
		} else if (width > maxW || height > maxH) {
			// 图片过大进行缩放
			int beW = 0;
			int beH = 0;
			int be = 1;
			if (maxH != 0 && maxW != 0) {
				beW = width / maxW;
				beH = height / maxH;
			}
			be = Math.max(beH, beW);
			if (be < 1) {
				be = 1;
			}
			options.inSampleSize = be;
			bm = BitmapFactory.decodeFile(path, options);
			// 重新测量长度，修改绝对的长和宽
			int scaleW = bm.getWidth();
			int scaleH = bm.getHeight();

			Matrix matrix = new Matrix();
			if (scaleH <= maxH && scaleW <= maxW) {
				return bm;
			}
			if (scaleH > maxH || scaleW > maxW) {
				float x = (float) maxW / (float) scaleW;
				float y = (float) maxH / (float) scaleH;
				if (x < y) {
					matrix.postScale(x, x);
				} else {
					matrix.postScale(y, y);
				}
				bm = Bitmap
						.createBitmap(bm, 0, 0, scaleW, scaleH, matrix, true);
				return bm;
			}

		}
		return null;
	}

	public static String datastream (Bitmap bm){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
		ByteArrayInputStream is = new ByteArrayInputStream(stream.toByteArray());
		String string = is.toString();
		return string;
	}

//	private String doPost(String url, Map<String, String> param, File file)
//			throws Exception {
//		HttpPost post = new HttpPost(url);
//		HttpClient client = new DefaultHttpClient();
//		MultipartEntity entity = new MultipartEntity();
//		if (param != null && !param.isEmpty()) {
//			for (Map.Entry<String, String> entry : param.entrySet()) {
//				entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
//			}
//		}
//		
//		// 添加文件参数
//		if (file != null && file.exists()) {
//			entity.addPart("file", new FileBody(file));
//		}
//		post.setEntity(entity);
//		HttpResponse response = client.execute(post);
//		int stateCode = response.getStatusLine().getStatusCode();
//		StringBuffer sb = new StringBuffer();
//		if (stateCode == HttpStatus.SC_OK) {
//			HttpEntity result = response.getEntity();
//			if (result != null) {
//				InputStream is = result.getContent();
//				BufferedReader br = new BufferedReader(
//						new InputStreamReader(is));
//				String tempLine;
//				while ((tempLine = br.readLine()) != null) {
//					sb.append(tempLine);
//				}
//			}
//		}
//		post.abort();
//		return sb.toString();
//	}
	/**
	 * 将图片转换成二进制流
	 * @param bitmap
	 * @return
	 */
	public static String getBitmapByte(Bitmap bitmap){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		//参数1转换类型，参数2压缩质量，参数3字节流资源
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray().toString();
	}


//	public Drawable getDrawableFromByte(byte[] temp){  
//	    if(temp != null){  
//	        Drawable drawable = Drawable.createFromStream(temp, "image");
//	        return drawable ;  
//	    }else{  
//	        return null;  
//	    }  
//	}  
	/**
	 *
	 * @param temp
	 * @return
	 */
//	public Drawable getDrawableFromByte(final byte[] temp){  
//	    if(temp != null){  
////	        Drawable drawable = Drawable.createFromStream(temp, "image");
//	    	Drawable drawable = Drawable.createFromStream((temp, "image");
//	        return drawable ;  
//	    }else{  
//	        return null;  
//	    }  
//	}  
	/**
	 * 将Drawable转换成Bitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable){

		int width = drawable.getIntrinsicWidth();

		int height = drawable.getIntrinsicHeight();

		Bitmap bitmap = Bitmap.createBitmap(width, height,

				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

						: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);

		drawable.setBounds(0,0,width,height);

		drawable.draw(canvas);

		return bitmap;



	}

	public static  Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;//这里设置高度为800f
		float ww = 480f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
	}
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while ( baos.toByteArray().length / 1024>100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;//每次都减少10
			Log.e("==image==","-----------------------------------------"+options);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}



	/**
	 * 根据图片的url路径获得Bitmap对象
	 * @param url
	 * @return
	 */
	public static  Bitmap returnBitmap(final  String url) {
		// Android 4.0 之后不能在主线程中请求HTTP请求


		URL fileUrl = null;
		Bitmap bitmap = null;

		try {
			fileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			HttpURLConnection conn = (HttpURLConnection) fileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static Bitmap getBitmapCompress(String imagePath) {

		// 设置参数
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
		int height = options.outHeight;
		int width= options.outWidth;
		int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
		int minLen = Math.min(height, width); // 原图的最小边长
		if (minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
			float ratio = (float)minLen / 180.0f; // 计算像素压缩比例
			inSampleSize = (int)ratio;
//			minLen = inSampleSize;
		}
		options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
		options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
		Bitmap bm = BitmapFactory.decodeFile(imagePath, options); // 解码文件
		return bm;
	}

	public static Bitmap imageZoom(Bitmap bitMap) {
		//图片允许最大空间   单位：KB
		double maxSize =10.00;
		//将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		//将字节换成KB
		double mid = b.length/1024;
		//判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
		if (mid > maxSize) {
			//获取bitmap大小 是允许最大大小的多少倍
			double i = mid / maxSize;
			//开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
			bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
					bitMap.getHeight() / Math.sqrt(i));
		}
		return bitMap;
	}

	/***
	 * 图片的缩放方法
	 *
	 * @param bgimage
	 *            ：源图片资源
	 * @param newWidth
	 *            ：缩放后宽度
	 * @param newHeight
	 *            ：缩放后高度
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
								   double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}
}
