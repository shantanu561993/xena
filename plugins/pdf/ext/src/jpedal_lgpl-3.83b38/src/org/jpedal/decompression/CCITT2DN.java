package org.jpedal.decompression;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jpedal.objects.raw.PdfObject;

public class CCITT2DN {
	
	private int EOL = -1;

	private long [][] white = {
			{1222, 2, 0},
			{2111, 3, 0},
			{2122, 4, 0},
			{2211, 5, 0},
			{2221, 6, 0},
			{2222, 7, 0},
			{21122, 8, 0},
			{21211, 9, 0},
			{11222, 10, 0},
			{12111, 11, 0},
			{22122, 64, 1},
			{21121, 128, 1},
			{111222, 1, 0},
			{112111, 12, 0},
			{111122, 13, 0},
			{221211, 14, 0},
			{221212, 15, 0},
			{212121, 16, 0},
			{212122, 17, 0},
			{121222, 192, 1},
			{122111, 1664, 1},
			{1211222, 18, 0},
			{1112211, 19, 0},
			{1112111, 20, 0},
			{1121222, 21, 0},
			{1111122, 22, 0},
			{1111211, 23, 0},
			{1212111, 24, 0},
			{1212122, 25, 0},
			{1121122, 26, 0},
			{1211211, 27, 0},
			{1122111, 28, 0},
			{1221222, 256, 1},
			{11221212, 0, 0},
			{11111121, 29, 0},
			{11111122, 30, 0},
			{11122121, 31, 0},
			{11122122, 32, 0},
			{11121121, 33, 0},
			{11121122, 34, 0},
			{11121211, 35, 0},
			{11121212, 36, 0},
			{11121221, 37, 0},
			{11121222, 38, 0},
			{11212111, 39, 0},
			{11212112, 40, 0},
			{11212121, 41, 0},
			{11212122, 42, 0},
			{11212211, 43, 0},
			{11212212, 44, 0},
			{11111211, 45, 0},
			{11111212, 46, 0},
			{11112121, 47, 0},
			{11112122, 48, 0},
			{12121121, 49, 0},
			{12121122, 50, 0},
			{12121211, 51, 0},
			{12121212, 52, 0},
			{11211211, 53, 0},
			{11211212, 54, 0},
			{12122111, 55, 0},
			{12122112, 56, 0},
			{12122121, 57, 0},
			{12122122, 58, 0},
			{12112121, 59, 0},
			{12112122, 60, 0},
			{11221121, 61, 0},
			{11221122, 62, 0},
			{11221211, 63, 0},
			{11221221, 320, 1},
			{11221222, 384, 1},
			{12211211, 448, 1},
			{12211212, 512, 1},
			{12212111, 576, 1},
			{12211222, 640, 1},
			{122112211, 704, 1},
			{122112212, 768, 1},
			{122121121, 832, 1},
			{122121122, 896, 1},
			{122121211, 960, 1},
			{122121212, 1024, 1},
			{122121221, 1088, 1},
			{122121222, 1152, 1},
			{122122111, 1216, 1},
			{122122112, 1280, 1},
			{122122121, 1344, 1},
			{122122122, 1408, 1},
			{121122111, 1472, 1},
			{121122112, 1536, 1},
			{121122121, 1600, 1},
			{121122122, 1728, 1},
			{11111112111L, 1792, 1},
			{11111112211L, 1856, 1},
			{11111112212L, 1920, 1},
			{111111111112L, EOL, 1},
			{111111121121L, 1984, 1},
			{111111121122L, 2048, 1},
			{111111121211L, 2112, 1},
			{111111121212L, 2176, 1},
			{111111121221L, 2240, 1},
			{111111121222L, 2304, 1},
			{111111122211L, 2368, 1},
			{111111122212L, 2432, 1},
			{111111122221L, 2496, 1},
			{111111122222L, 2560, 1}
	};
	
	private long [][] black = {
			{22, 2, 0},
			{21, 3, 0},		
			{121, 1, 0},
			{122, 4, 0},
			{1122, 5, 0},
			{1121, 6, 0},
			{11122, 7, 0},
			{111212, 8,0},
			{111211, 9,0},
			{1111211, 10, 0},
			{1111212, 11, 0},
			{1111222, 12, 0},
			{11111211, 13, 0},
			{11111222, 14, 0},
			{111122111, 15, 0},
			{1111221222, 0, 0},
			{1111121222, 16, 0},
			{1111122111, 17, 0},
			{1111112111, 18, 0},
			{1111112222, 64, 1},
			{11112211222L, 19, 0},
			{11112212111L, 20, 0},
			{11112212211L, 21, 0},
			{11111221222L, 22, 0},
			{11111212111L, 23, 0},
			{11111121222L, 24, 0},
			{11111122111L, 25, 0},
			{11111112111L, 1792, 1},
			{11111112211L, 1856, 1},
			{11111112212L, 1920, 1},	
			{111122112121L, 26, 0},
			{111122112122L, 27, 0},
			{111122112211L, 28, 0},
			{111122112212L, 29, 0},
			{111112212111L, 30, 0},
			{111112212112L, 31, 0},
			{111112212121L, 32, 0},
			{111112212122L, 33, 0},
			{111122121121L, 34, 0},
			{111122121122L, 35, 0},
			{111122121211L, 36, 0},
			{111122121212L, 37, 0},
			{111122121221L, 38, 0},
			{111122121222L, 39, 0},
			{111112212211L, 40, 0},
			{111112212212L, 41, 0},
			{111122122121L, 42, 0},
			{111122122122L, 43, 0},
			{111112121211L, 44, 0},
			{111112121212L, 45, 0},
			{111112121221L, 46, 0},
			{111112121222L, 47, 0},
			{111112211211L, 48, 0},
			{111112211212L, 49, 0},
			{111112121121L, 50, 0},
			{111112121122L, 51, 0},
			{111111211211L, 52, 0},
			{111111221222L, 53, 0},
			{111111222111L, 54, 0},
			{111111211222L, 55, 0},
			{111111212111L, 56, 0},
			{111112122111L, 57, 0},
			{111112122112L, 58, 0},
			{111111212122L, 59, 0},
			{111111212211L, 60, 0},
			{111112122121L, 61, 0},
			{111112211221L, 62, 0},
			{111112211222L, 63, 0},
			{111122112111L, 128, 1},
			{111122112112L, 192, 1},
			{111112122122L, 256, 1},
			{111111221122L, 320, 1},
			{111111221211L, 384, 1},
			{111111221212L, 448, 1},
			{111111111112L, EOL, 1},
			{111111121121L, 1984, 1},
			{111111121122L, 2048, 1},
			{111111121211L, 2112, 1},
			{111111121212L, 2176, 1},
			{111111121221L, 2240, 1},
			{111111121222L, 2304, 1},
			{111111122211L, 2368, 1},
			{111111122212L, 2432, 1},
			{111111122221L, 2496, 1},
			{111111122222L, 2560, 1},
			{1111112212211L, 512, 1},
			{1111112212212L, 576, 1},
			{1111112112121L, 640, 1},
			{1111112112122L, 704, 1},
			{1111112112211L, 768, 1},
			{1111112112212L, 832, 1},
			{1111112221121L, 896, 1},
			{1111112221122L, 960, 1},
			{1111112221211L, 1024, 1},
			{1111112221212L, 1088, 1},
			{1111112221221L, 1152, 1},
			{1111112221222L, 1216, 1},
			{1111112121121L, 1280, 1},
			{1111112121122L, 1344, 1},
			{1111112121211L, 1408, 1},
			{1111112121212L, 1472, 1},
			{1111112122121L, 1536, 1},
			{1111112122122L, 1600, 1},
			{1111112211211L, 1664, 1},
			{1111112211212L, 1728, 1}
	};
	
	private int [][] modes = {
			{2,1},
			{122,2},
			{121,3},
			{112,4},
			{1112,5},
			{111122,6},
			{111121,7},
			{1111122,8},
			{1111121,9}
	};
	
	private int width=0;
	private int height=0;
	private int bsLength = 0;

	private BitSet outSet;
	private CusotomBitSet dataSet = null;
	
	private byte[] data = null;
	private int[] referenceLine = null;
	private int refPtr = 0;
	private int[] codingLine = null;
	private int codeLnPtr = 0;
	private BitSet tmpSet = new BitSet();
	private int tmpPtr = 0;
	
	private boolean isTerminating = false;
	private boolean isWhite = true;
	private boolean color = false;
	
	
	public CCITT2DN(byte[] data, int w, int h,PdfObject p){
		
		data = clean(data);
		this.data = data;
		width = w;
		height = h;
		outSet = this.fromByteArray(data);
		dataSet = new CusotomBitSet(outSet,bsLength);
		referenceLine = new int[w];
		codingLine = new int[w];
		
		System.out.println("<INFO> width="+width+" height="+height + " data.length="+data.length);	
	}
	
	public byte[] decode(){
		//dataSet.printData(0, 140);
		
		// single char from bit code
		int digit = dataSet.getSingleDigit();
		// operation code
		int opCode = 0;
		// corresponding operation mode
		int opMode = 0;
		
		while(digit!=0){
			//first thing to do is to identify the mode we will be working in
			opCode = (opCode*10)+digit;
			for(int i=0;i<modes.length;i++){
				if(opCode == modes[i][0]){
					// here we know we have found a valid mode
					opMode = modes[i][1];
					//System.out.println("<INFO> Mode identified as "+modes[i][1]);
					
					switch(opMode){
						case(1): // V0
							System.out.println("mode = V0");
							this.V0();
							break;
						
						case(2): // VR1
							System.out.println("mode = VR1");
							this.VR1();
							break;
						
						case(3): // VL1
							System.out.println("mode = VL1");
							this.VL1();
							break;
						
						case(4): // Horiz
							System.out.println("mode = Horiz");
							this.Horiz();
							break;
						
						case(5): // Pass
							System.out.println("mode = Pass");
							this.Pass();
							break;
						
						case(6): // VR2
							System.out.println("mode = VR2");
							this.VR2();
							break;
						
						case(7): // VL2
							System.out.println("mode = VL2");
							this.VL2();
							break;
						
						case(8): // VR3
							System.out.println("mode = VR3");
							this.VR3();
							break;
						
						case(9): // VL3
							System.out.println("mode = VL3");
							this.VL3();
							break;
					}
					
					this.checkEOL();
					
					opCode = 0;
				}
			}

			// get the next element before we loop again
			digit = dataSet.getSingleDigit();
		}

		this.showImage();
		
		return null;
	}

	private void showImage() {
		
		int temp = width % 8;
		int temp2 = width / 8;		
		int temp3 = 0;
		
		if(temp==0){
			temp3 = temp2;
		}else{
			temp3 = temp2+1;
		}
		
		byte[] output = new byte[(temp3)*(height)];
		
		
		int bytePtr = 0;
		int bitPtr = 7;
		int mask = 0;
		byte entry = 0;

		for(int j=0;j<tmpPtr;j++){

			if(tmpSet.get(j)==true){
				mask = 1 << bitPtr;

				entry |= mask;
				bitPtr--;
			}else{
				bitPtr--;
			}
			
			if(((j+1)%(width))==0 && (j!=0)){
				bitPtr = -1;
			}

			if(bitPtr < 0){		
				output[bytePtr]=entry;
				bytePtr++;
				bitPtr = 7;
				entry = 0;
			}
		}


		
        //make a a DEEP copy so we cant alter
        int len=output.length;
        byte[] copy=new byte[len];
        System.arraycopy(output, 0, copy, 0, len);

		
		/** create an image from the raw data */
		DataBuffer db = new DataBufferByte(copy, copy.length);
		
		WritableRaster raster = Raster.createPackedRaster(db, width, height, 1, null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		image.setData(raster);

		Image scaledImage = image.getScaledInstance(500, -1, Image.SCALE_AREA_AVERAGING);

		BufferedImage result = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_BGR);
		Graphics2D g = result.createGraphics();
		g.drawImage(scaledImage, 0, 0, null);
		g.dispose();

		JLabel label = new JLabel(new ImageIcon(result));
		
		JOptionPane.showConfirmDialog(null, label, "JBIG2 Display", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		
	}

	private void checkEOL() {
		if(codeLnPtr == codingLine.length){
			// --------------------
			for(int i=0;i<codingLine.length;i++){
				
				if(codingLine[i]==0){
					tmpSet.set(tmpPtr, true);
				}else if(codingLine[i]==1){
					tmpSet.set(tmpPtr, false);
				}
				
				tmpPtr++;
			}
			// --------------------
			
			System.out.println("One full line done ");
			System.arraycopy(codingLine, 0, referenceLine, 0, referenceLine.length);
			refPtr = 0;
			codeLnPtr = 0;
			color = true;
			codingLine = new int[width];
		}
		
	}
	
	private void Horiz() {
		
		int run = this.getRun();
		
			
		for(int i=0;i<run;i++){
			if(!color){
				codingLine[codeLnPtr] = 1;
			}else{
				codingLine[codeLnPtr] = 0;
			}
			
			codeLnPtr++;
		}
		
		if(run>63){
			
			run = this.getRun();
	
				
			for(int i=0;i<run;i++){
				if(!color){
					codingLine[codeLnPtr] = 1;
				}else{
					codingLine[codeLnPtr] = 0;
				}
				
				codeLnPtr++;
			}
		}
		
		run = this.getRun();

			
		for(int i=0;i<run;i++){
			if(!color){
				codingLine[codeLnPtr] = 1;
			}else{
				codingLine[codeLnPtr] = 0;
			}
			
			codeLnPtr++;
		}
		
		if(run>63){
			
			run = this.getRun();
	
				
			for(int i=0;i<run;i++){
				if(!color){
					codingLine[codeLnPtr] = 1;
				}else{
					codingLine[codeLnPtr] = 0;
				}
				
				codeLnPtr++;
			}
		}
		
		
	
	
		refPtr = codeLnPtr;
	}
	
	private void Pass() {
		this.showImage();
		try {
			Thread.sleep(2000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void V0() {
		
		// works for now we'll need to check on othger files
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
		
	}
	
	private void VL1() {
		codeLnPtr--;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
	}
	
	private void VL2() {
		codeLnPtr = codeLnPtr - 2;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
	}
	
	
	private void VL3() {
		codeLnPtr = codeLnPtr - 3;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
	}
	
	
	private void VR1() {
		codeLnPtr++;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
	}
	
	
	
	private void VR2() {
		codeLnPtr = codeLnPtr + 2;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}
	}
	
	private void VR3() {
		codeLnPtr = codeLnPtr + 3;
		
		if(refPtr != referenceLine.length){
			
			for(;refPtr<referenceLine.length;){
				
				codingLine[codeLnPtr] = referenceLine[refPtr];
				codeLnPtr++;
				refPtr++;
				
				if(refPtr == referenceLine.length){
					return;
				}else if(referenceLine[refPtr]!=referenceLine[refPtr-1]){
					return;
				}
			
			}
			
			codeLnPtr = refPtr;
			
		}else{
			System.out.println("<ERROR> Reference line pointer error refPtr = " +refPtr);
		}	
	}
	

	private static byte[] clean(byte[] data) {
		byte[] ret = null;
		int ptr = 0;

		
		if(data[0]==32||data[0]==10||data[0]==0){
			ptr = 1;
		}
		
		if(data[1]==32||data[1]==10||data[1]==1){
			ptr = 2;
		}
		
		ret = new byte[data.length-ptr];
		
		for(int i = ptr,q=0;i<data.length;i++,q++){
			ret[q] = data[i];
		}
		
		return ret;
	}
	
	private BitSet fromByteArray(byte[] bytes) {
		
		int bitSetPtr = 0;
		byte tmp;
		int value = 0;
        BitSet bits = new BitSet();
        for (int i=0; i<bytes.length; i++) {
        	tmp=bytes[i];
        	for(int z=7;z>=0;z--){
        		
        		value = (tmp & (1 << z));
        		
        		if(value>=1)
        			bits.set(bitSetPtr,true);

        		bitSetPtr++;
        	}
        }
        
        // will give a true length of bs as bs.lenght only counts till the last 1 in stream!
        bsLength = bitSetPtr;
        
        return bits;
        
	}
	
	private int getRun(){
		int tmp = 0;
		int res = -111;
		long match = 0L;
		
		for(int i=0;i<13;i++){

			tmp = dataSet.getSingleDigit();
			
			//System.out.println("<INFO> " + dataSet.getCbsPtr() + " " + tmp);

			if(i==0){
				match = tmp;
			}else{
				match = (match*10)+tmp;
			}
			
			// make sure code compare happends INSIDE the for loop
			res = findMatch(match);
			//System.out.println("match="+match);
			if(res!=-111){
				//System.out.println("Match found!!!");
				System.out.println("res="+res);
				return res;
			}
		}

		return res;
	}
	
	public int findMatch(long match){
		boolean found = false;
		
		if(isWhite){
			for(int i=0;i<white.length && !found;i++){
				if(match==white[i][0]){
					found = true;
					int res = (int)white[i][1];
					
					if(res>63){
						//isWhite = !isWhite;
						isTerminating = true;
					}else if(res==-1){
						isWhite = true;
						isTerminating = true;
					}else{
						isWhite = !isWhite;
						isTerminating = false;
					}
					
					color = true;
					
					return res;
				}
			}
		}else{
			for(int i=0;i<black.length && !found;i++){
				if(match==black[i][0]){
					found = true;
					int res = (int)black[i][1];
					
					if(res>63){
						isTerminating = true;
					}else if(res==-1){
						isWhite = true;
						isTerminating = true;
					}else{
						isWhite = !isWhite;
						isTerminating = false;
					}
					
					return res;
				}
			}
			
			color = false;
		}

		return -111;
	}

}
