package jpegdemo;
import  java.awt.image.RenderedImage;
import  java.io.ByteArrayOutputStream;
import  java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;



public class Main {

	public static void main(String[] args) {
		File originalImage=new File("C:\\Users\\Monster\\Desktop\\original.jpg");
		File compressedImage=new File("C:\\Users\\Monster\\Desktop\\compressedImage.jpg");
		
		try {
			//byte[] imageData=getCompressedImageBytes(originalImage, 0.5f);
			compressJPEGImage(originalImage,compressedImage,0.5f);
			//System.out.println(imageData.length);
			System.out.println("Done!");
			
		}
		catch(IOException e) {
			
		}
		
		
	}
	
	public static void compressJPEGImage(File originalImage,File compressedImage,float comressionQality) throws IOException{
		RenderedImage image=ImageIO.read(originalImage);
		ImageWriter jpegWriter=ImageIO.getImageWritersByFormatName("jpg").next();
		ImageWriteParam jpegWriteParam=jpegWriter.getDefaultWriteParam();
		jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegWriteParam.setCompressionQuality(comressionQality);
		
		try(ImageOutputStream output=ImageIO.createImageOutputStream(compressedImage)){
			jpegWriter.setOutput(output);;
			IIOImage outputImage=new IIOImage(image,null,null);
			jpegWriter.write(null,outputImage,jpegWriteParam);
			}
		jpegWriter.dispose();
		
		
	}
 
	/*public static byte[] getCompressedImageBytes(File originalImage,float compressionQuality)throws IOException{
		byte[] compressedImageBytes;
		
		RenderedImage image=ImageIO.read(originalImage);
		ImageWriter jpegWriter=ImageIO.getImageWritersByFormatName("jpg").next();
		ImageWriteParam jpegWriteParam=jpegWriter.getDefaultWriteParam();
		jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegWriteParam.setCompressionQuality(compressionQuality);
		
		try(ByteArrayOutputStream baos=new ByteArrayOutputStream(); ImageOutputStream output=new MemoryCacheImageOutputStream(baos)){
			jpegWriter.setOutput(output);;
			IIOImage outputImage=new IIOImage(image,null,null);
			jpegWriter.write(null,outputImage,jpegWriteParam);
			compressedImageBytes=baos.toByteArray();
			}
		jpegWriter.dispose();		
		return compressedImageBytes;*/
	}

