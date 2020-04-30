package objectRef;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ImageLoader extends BufferedImage{

	private AffineTransform at;
	private AffineTransformOp rotateOp;
	
	public ImageLoader(BufferedImage loadimg) {
		super(loadimg.getWidth(), loadimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		at=new AffineTransform();
		at.translate(getWidth()/ 2, getHeight() / 2);
		at.rotate(Math.PI);
		at.translate(-getWidth()/ 2, -getHeight() / 2);
		rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	rotateOp.filter(loadimg, this);
		
	}

	public ImageLoader colorImage(Color c) {
        int width = this.getWidth();
        int height = this.getHeight();
    
        
        WritableRaster raster = this.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = c.getRed();
                pixels[1] = c.getGreen();
                pixels[2] = c.getBlue();
                raster.setPixel(xx, yy, pixels);
            }
        }
            return this;
 }
	
	
}
