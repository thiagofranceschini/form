package br.com.izifinance.iziregister.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;
	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile, String registerId) throws IOException, URISyntaxException {
		String fileName = "izibank/"+registerId;
		InputStream inputStream = multipartFile.getInputStream();
		String contentType = multipartFile.getContentType();
		return uploadFile(inputStream, fileName, contentType);
	}

	public URI uploadFile(InputStream inputStream, String fileName, String contentType) throws URISyntaxException {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		LOG.info("iniciando upload :" + fileName);
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, meta);
		putObjectRequest.setAccessControlList(acl);
		s3Client.putObject(putObjectRequest);
		LOG.info("URI gerada: "+s3Client.getUrl(bucketName, fileName).toString());
		return s3Client.getUrl(bucketName, fileName).toURI();
	}
	
	

}
