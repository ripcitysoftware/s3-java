package com.ripcitysoftware.aws;

import com.amazonaws.services.devicefarm.model.Run;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.List;
import java.util.stream.Collectors;

public class S3StorageService {

  AmazonS3 s3;

  public void init() {
    s3  = AmazonS3ClientBuilder.standard().build();
  }

  public List<String> listObjects() {
    List<S3ObjectSummary> summaries = null;
    String bucketName = "ripcitysoftware";

    try {
      ObjectListing objectListing = s3.listObjects(bucketName);
      summaries = objectListing.getObjectSummaries();
    } catch(Exception e) {
      throw new StorageException("Failed to list objects for buckert",e);
    }

    return summaries.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
  }
}
