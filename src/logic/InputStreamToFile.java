package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class InputStreamToFile {
	private InputStream inputStream;
	private String filePath;
	
	public InputStreamToFile(InputStream inputStream, String filePath) {
		this.inputStream = inputStream;
		this.filePath = filePath;
	}
	
	public File getFile(){
		OutputStream outputStream = null;
		File file = new File(filePath);
		
		try {
			outputStream = 
					new FileOutputStream(file);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return file;
	}
}
