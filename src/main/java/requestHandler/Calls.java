package requestHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Calls {
	static String BASE_API="https://databridge.onrender.com";
	static OkHttpClient client = new OkHttpClient(); 	
	
	
	 private static String getMediaType(String fileName) {
	        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
	        FileNameMap fileNameMap = URLConnection.getFileNameMap();
	        String mediaType = fileNameMap.getContentTypeFor(fileName);
	        return mediaType;
//	        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
	    }
	
	
	
	public static void makePingRequest() {
	Request pingRequest = new Request.Builder()
			.url(BASE_API+"/ping")
			.get()
			.build();
	
	try {
	Response pingResponse = client.newCall(pingRequest).execute();
	System.out.println(pingResponse.body().string());
	pingResponse.close();
	}catch(Exception e) 
	{ System.out.println("Unable to connect with API!"+e.getMessage());}
	
	
	}	
	
	
	public static List<List<String>> getFileList() {
		String FILE_LIST_URL=BASE_API+"/list";
		Request listRequest = new Request.Builder().url(FILE_LIST_URL).get().build();
		
		try {
			 Response listResponse = client.newCall(listRequest).execute();
//		System.out.println(listResponse.body().string());
		
		
		 if (listResponse.isSuccessful()) {
	            Gson gson = new Gson();
	            Type type = new TypeToken<List<List<String>>>() {}.getType();
	            List<List<String>> fileList = gson.fromJson(listResponse.body().string(), type);
	            return fileList;
		 }
		 return null;
		}catch (Exception e) {System.out.println("list response failed");return null;}
		
	}
	
	
	
	
	
	
	
	
	public static void downloadFile(String fileName) {
        String savePath = String.format(System.getProperty("user.home")+"\\Downloads\\%s", fileName);
        String DOWNLOAD_URL = String.format("%s/download/%s", BASE_API, fileName);

        System.out.println(DOWNLOAD_URL);

        Request downloadReq = new Request.Builder().url(DOWNLOAD_URL).get().build();

        try (Response response = client.newCall(downloadReq).execute()) {
            if (response.isSuccessful()) {
                ResponseBody rb = response.body();

                if (rb != null) {
                    System.out.println("ResponseBody Is Not NULL");

                    try (InputStream inputStream = rb.byteStream();
                         FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }

                        System.out.println("File downloaded successfully!");

                    } catch (IOException e) {
                        System.out.println("Error while reading/writing to file: " + e.getMessage());
                    }
                } else {
                    System.out.println("Response body is null.");
                }
            } else {
                System.out.println("Download request failed with code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Download request failed: " + e.getMessage());
        }
    }
	
	
	
	

	public static void uploadFile(String filePath) {
        File fileToUpload = new File(filePath);
        String UPLOAD_URL = BASE_API + "/upload";

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileToUpload.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), fileToUpload))
                .build();

        Request uploadRequest = new Request.Builder()
                .url(UPLOAD_URL)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(uploadRequest).execute()) {
            if (response.isSuccessful()) {
                System.out.println("File uploaded successfully!");
                System.out.println("Server response: " + response.body().string());
            } else {
                System.out.println("Upload request failed with code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Upload request failed: " + e.getMessage());
        }
    }
	
	
	
}