package com.lingber.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;

/** 
* @author 作者 Lingber 414393125@qq.com: 
* @version 创建时间：2018年5月3日 下午5:35:02 
* 类说明 :
*/
@Service("oss_service")
public class oss_service {
	
	public void uploadFile_to_oss(String file_path,String file_key) throws FileNotFoundException {
		String oss_bucket ="#";
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
		String accessKeyId = "#";
		String accessKeySecret = "#";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		//1、上传文件流
		InputStream inputStream = new FileInputStream(file_path);
		ossClient.putObject(oss_bucket, file_key, inputStream);
		
		// 关闭ossClient
		ossClient.shutdown();
	}
	
	public URL get_oss_url(String file_key) {
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
		String accessKeyId = "#";
		String accessKeySecret = "#";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		//1、生成url
		String bucketName = "lingber";
		// 设置URL过期时间为1小时
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		// 生成URL
		URL url = null;
		try {
			url = ossClient.generatePresignedUrl(bucketName, file_key, expiration);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			url =null;
		}
		System.out.println(url);
		// 关闭ossClient
		ossClient.shutdown();
		
		return url;
	}
	
	public void del_oss(String file_key) {
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
		String accessKeyId = "#";
		String accessKeySecret = "#";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		String bucketName = "#";
		// 删除Object
		ossClient.deleteObject(bucketName, file_key);
		
		// 关闭ossClient
		ossClient.shutdown();
		
	}
	
	
	public void del_oss_s(List<String> file_keys) {
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
		String accessKeyId = "#";
		String accessKeySecret = "#";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		String bucketName = "#";
		
		// 删除Objects
		List<String> keys = new ArrayList<String>();
		keys.add("key0");
		keys.add("key1");
		keys.add("key2");
		DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
		List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
		
		// 关闭ossClient
		ossClient.shutdown();
		
	}
	
	

/*	public static void main(String[] args) {
		get_oss_url("f4d6493ef12c4884b84732d33d2d3f59.jpg");
	}*/
	
}
