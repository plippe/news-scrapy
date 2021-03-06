package com.github.plippe.news.scrapy.mocks

import com.amazonaws.services.s3.AmazonS3

class AmazonS3Mock extends AmazonS3 {
  def abortMultipartUpload(
      x$1: com.amazonaws.services.s3.model.AbortMultipartUploadRequest): Unit =
    ???
  def changeObjectStorageClass(
      x$1: String,
      x$2: String,
      x$3: com.amazonaws.services.s3.model.StorageClass): Unit = ???
  def completeMultipartUpload(
      x$1: com.amazonaws.services.s3.model.CompleteMultipartUploadRequest)
    : com.amazonaws.services.s3.model.CompleteMultipartUploadResult = ???
  def copyObject(x$1: com.amazonaws.services.s3.model.CopyObjectRequest)
    : com.amazonaws.services.s3.model.CopyObjectResult = ???
  def copyObject(
      x$1: String,
      x$2: String,
      x$3: String,
      x$4: String): com.amazonaws.services.s3.model.CopyObjectResult = ???
  def copyPart(x$1: com.amazonaws.services.s3.model.CopyPartRequest)
    : com.amazonaws.services.s3.model.CopyPartResult = ???
  def createBucket(x$1: String,
                   x$2: String): com.amazonaws.services.s3.model.Bucket = ???
  def createBucket(x$1: String, x$2: com.amazonaws.services.s3.model.Region)
    : com.amazonaws.services.s3.model.Bucket = ???
  def createBucket(x$1: String): com.amazonaws.services.s3.model.Bucket = ???
  def createBucket(x$1: com.amazonaws.services.s3.model.CreateBucketRequest)
    : com.amazonaws.services.s3.model.Bucket = ???
  def deleteBucket(x$1: String): Unit = ???
  def deleteBucket(
      x$1: com.amazonaws.services.s3.model.DeleteBucketRequest): Unit = ???
  def deleteBucketAnalyticsConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketAnalyticsConfigurationRequest)
    : com.amazonaws.services.s3.model.DeleteBucketAnalyticsConfigurationResult =
    ???
  def deleteBucketAnalyticsConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.DeleteBucketAnalyticsConfigurationResult =
    ???
  def deleteBucketCrossOriginConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketCrossOriginConfigurationRequest)
    : Unit = ???
  def deleteBucketCrossOriginConfiguration(x$1: String): Unit = ???
  def deleteBucketEncryption(
      x$1: com.amazonaws.services.s3.model.DeleteBucketEncryptionRequest)
    : com.amazonaws.services.s3.model.DeleteBucketEncryptionResult = ???
  def deleteBucketEncryption(x$1: String)
    : com.amazonaws.services.s3.model.DeleteBucketEncryptionResult = ???
  def deleteBucketInventoryConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketInventoryConfigurationRequest)
    : com.amazonaws.services.s3.model.DeleteBucketInventoryConfigurationResult =
    ???
  def deleteBucketInventoryConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.DeleteBucketInventoryConfigurationResult =
    ???
  def deleteBucketLifecycleConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketLifecycleConfigurationRequest)
    : Unit = ???
  def deleteBucketLifecycleConfiguration(x$1: String): Unit = ???
  def deleteBucketMetricsConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketMetricsConfigurationRequest)
    : com.amazonaws.services.s3.model.DeleteBucketMetricsConfigurationResult =
    ???
  def deleteBucketMetricsConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.DeleteBucketMetricsConfigurationResult =
    ???
  def deleteBucketPolicy(
      x$1: com.amazonaws.services.s3.model.DeleteBucketPolicyRequest): Unit =
    ???
  def deleteBucketPolicy(x$1: String): Unit = ???
  def deleteBucketReplicationConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketReplicationConfigurationRequest)
    : Unit = ???
  def deleteBucketReplicationConfiguration(x$1: String): Unit = ???
  def deleteBucketTaggingConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketTaggingConfigurationRequest)
    : Unit = ???
  def deleteBucketTaggingConfiguration(x$1: String): Unit = ???
  def deleteBucketWebsiteConfiguration(
      x$1: com.amazonaws.services.s3.model.DeleteBucketWebsiteConfigurationRequest)
    : Unit = ???
  def deleteBucketWebsiteConfiguration(x$1: String): Unit = ???
  def deleteObject(
      x$1: com.amazonaws.services.s3.model.DeleteObjectRequest): Unit = ???
  def deleteObject(x$1: String, x$2: String): Unit = ???
  def deleteObjectTagging(
      x$1: com.amazonaws.services.s3.model.DeleteObjectTaggingRequest)
    : com.amazonaws.services.s3.model.DeleteObjectTaggingResult = ???
  def deleteObjects(x$1: com.amazonaws.services.s3.model.DeleteObjectsRequest)
    : com.amazonaws.services.s3.model.DeleteObjectsResult = ???
  def deleteVersion(
      x$1: com.amazonaws.services.s3.model.DeleteVersionRequest): Unit = ???
  def deleteVersion(x$1: String, x$2: String, x$3: String): Unit = ???
  def disableRequesterPays(x$1: String): Unit = ???
  def doesBucketExist(x$1: String): Boolean = ???
  def doesBucketExistV2(x$1: String): Boolean = ???
  def doesObjectExist(x$1: String, x$2: String): Boolean = ???
  def enableRequesterPays(x$1: String): Unit = ???
  def generatePresignedUrl(
      x$1: com.amazonaws.services.s3.model.GeneratePresignedUrlRequest)
    : java.net.URL = ???
  def generatePresignedUrl(x$1: String,
                           x$2: String,
                           x$3: java.util.Date,
                           x$4: com.amazonaws.HttpMethod): java.net.URL = ???
  def generatePresignedUrl(x$1: String,
                           x$2: String,
                           x$3: java.util.Date): java.net.URL = ???
  def getBucketAccelerateConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketAccelerateConfiguration = ???
  def getBucketAccelerateConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketAccelerateConfiguration = ???
  def getBucketAcl(x$1: com.amazonaws.services.s3.model.GetBucketAclRequest)
    : com.amazonaws.services.s3.model.AccessControlList = ???
  def getBucketAcl(
      x$1: String): com.amazonaws.services.s3.model.AccessControlList = ???
  def getBucketAnalyticsConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketAnalyticsConfigurationRequest)
    : com.amazonaws.services.s3.model.GetBucketAnalyticsConfigurationResult =
    ???
  def getBucketAnalyticsConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.GetBucketAnalyticsConfigurationResult =
    ???
  def getBucketCrossOriginConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketCrossOriginConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketCrossOriginConfiguration = ???
  def getBucketCrossOriginConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketCrossOriginConfiguration = ???
  def getBucketEncryption(
      x$1: com.amazonaws.services.s3.model.GetBucketEncryptionRequest)
    : com.amazonaws.services.s3.model.GetBucketEncryptionResult = ???
  def getBucketEncryption(
      x$1: String): com.amazonaws.services.s3.model.GetBucketEncryptionResult =
    ???
  def getBucketInventoryConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketInventoryConfigurationRequest)
    : com.amazonaws.services.s3.model.GetBucketInventoryConfigurationResult =
    ???
  def getBucketInventoryConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.GetBucketInventoryConfigurationResult =
    ???
  def getBucketLifecycleConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketLifecycleConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketLifecycleConfiguration = ???
  def getBucketLifecycleConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketLifecycleConfiguration = ???
  def getBucketLocation(
      x$1: com.amazonaws.services.s3.model.GetBucketLocationRequest): String =
    ???
  def getBucketLocation(x$1: String): String = ???
  def getBucketLoggingConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketLoggingConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketLoggingConfiguration = ???
  def getBucketLoggingConfiguration(
      x$1: String): com.amazonaws.services.s3.model.BucketLoggingConfiguration =
    ???
  def getBucketMetricsConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketMetricsConfigurationRequest)
    : com.amazonaws.services.s3.model.GetBucketMetricsConfigurationResult = ???
  def getBucketMetricsConfiguration(x$1: String, x$2: String)
    : com.amazonaws.services.s3.model.GetBucketMetricsConfigurationResult = ???
  def getBucketNotificationConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketNotificationConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketNotificationConfiguration = ???
  def getBucketNotificationConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketNotificationConfiguration = ???
  def getBucketPolicy(
      x$1: com.amazonaws.services.s3.model.GetBucketPolicyRequest)
    : com.amazonaws.services.s3.model.BucketPolicy = ???
  def getBucketPolicy(
      x$1: String): com.amazonaws.services.s3.model.BucketPolicy = ???
  def getBucketReplicationConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketReplicationConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketReplicationConfiguration = ???
  def getBucketReplicationConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketReplicationConfiguration = ???
  def getBucketTaggingConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketTaggingConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketTaggingConfiguration = ???
  def getBucketTaggingConfiguration(
      x$1: String): com.amazonaws.services.s3.model.BucketTaggingConfiguration =
    ???
  def getBucketVersioningConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketVersioningConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketVersioningConfiguration = ???
  def getBucketVersioningConfiguration(x$1: String)
    : com.amazonaws.services.s3.model.BucketVersioningConfiguration = ???
  def getBucketWebsiteConfiguration(
      x$1: com.amazonaws.services.s3.model.GetBucketWebsiteConfigurationRequest)
    : com.amazonaws.services.s3.model.BucketWebsiteConfiguration = ???
  def getBucketWebsiteConfiguration(
      x$1: String): com.amazonaws.services.s3.model.BucketWebsiteConfiguration =
    ???
  def getCachedResponseMetadata(x$1: com.amazonaws.AmazonWebServiceRequest)
    : com.amazonaws.services.s3.S3ResponseMetadata = ???
  def getObject(
      x$1: com.amazonaws.services.s3.model.GetObjectRequest,
      x$2: java.io.File): com.amazonaws.services.s3.model.ObjectMetadata = ???
  def getObject(x$1: com.amazonaws.services.s3.model.GetObjectRequest)
    : com.amazonaws.services.s3.model.S3Object = ???
  def getObject(x$1: String,
                x$2: String): com.amazonaws.services.s3.model.S3Object = ???
  def getObjectAcl(x$1: com.amazonaws.services.s3.model.GetObjectAclRequest)
    : com.amazonaws.services.s3.model.AccessControlList = ???
  def getObjectAcl(
      x$1: String,
      x$2: String,
      x$3: String): com.amazonaws.services.s3.model.AccessControlList = ???
  def getObjectAcl(
      x$1: String,
      x$2: String): com.amazonaws.services.s3.model.AccessControlList = ???
  def getObjectAsString(x$1: String, x$2: String): String = ???
  def getObjectMetadata(
      x$1: com.amazonaws.services.s3.model.GetObjectMetadataRequest)
    : com.amazonaws.services.s3.model.ObjectMetadata = ???
  def getObjectMetadata(
      x$1: String,
      x$2: String): com.amazonaws.services.s3.model.ObjectMetadata = ???
  def getObjectTagging(
      x$1: com.amazonaws.services.s3.model.GetObjectTaggingRequest)
    : com.amazonaws.services.s3.model.GetObjectTaggingResult = ???
  def getRegion(): com.amazonaws.services.s3.model.Region = ???
  def getRegionName(): String = ???
  def getS3AccountOwner(
      x$1: com.amazonaws.services.s3.model.GetS3AccountOwnerRequest)
    : com.amazonaws.services.s3.model.Owner = ???
  def getS3AccountOwner(): com.amazonaws.services.s3.model.Owner = ???
  def getUrl(x$1: String, x$2: String): java.net.URL = ???
  def headBucket(x$1: com.amazonaws.services.s3.model.HeadBucketRequest)
    : com.amazonaws.services.s3.model.HeadBucketResult = ???
  def initiateMultipartUpload(
      x$1: com.amazonaws.services.s3.model.InitiateMultipartUploadRequest)
    : com.amazonaws.services.s3.model.InitiateMultipartUploadResult = ???
  def isRequesterPaysEnabled(x$1: String): Boolean = ???
  def listBucketAnalyticsConfigurations(
      x$1: com.amazonaws.services.s3.model.ListBucketAnalyticsConfigurationsRequest)
    : com.amazonaws.services.s3.model.ListBucketAnalyticsConfigurationsResult =
    ???
  def listBucketInventoryConfigurations(
      x$1: com.amazonaws.services.s3.model.ListBucketInventoryConfigurationsRequest)
    : com.amazonaws.services.s3.model.ListBucketInventoryConfigurationsResult =
    ???
  def listBucketMetricsConfigurations(
      x$1: com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsRequest)
    : com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsResult =
    ???
  def listBuckets(x$1: com.amazonaws.services.s3.model.ListBucketsRequest)
    : java.util.List[com.amazonaws.services.s3.model.Bucket] = ???
  def listBuckets(): java.util.List[com.amazonaws.services.s3.model.Bucket] =
    ???
  def listMultipartUploads(
      x$1: com.amazonaws.services.s3.model.ListMultipartUploadsRequest)
    : com.amazonaws.services.s3.model.MultipartUploadListing = ???
  def listNextBatchOfObjects(
      x$1: com.amazonaws.services.s3.model.ListNextBatchOfObjectsRequest)
    : com.amazonaws.services.s3.model.ObjectListing = ???
  def listNextBatchOfObjects(x$1: com.amazonaws.services.s3.model.ObjectListing)
    : com.amazonaws.services.s3.model.ObjectListing = ???
  def listNextBatchOfVersions(
      x$1: com.amazonaws.services.s3.model.ListNextBatchOfVersionsRequest)
    : com.amazonaws.services.s3.model.VersionListing = ???
  def listNextBatchOfVersions(
      x$1: com.amazonaws.services.s3.model.VersionListing)
    : com.amazonaws.services.s3.model.VersionListing = ???
  def listObjects(x$1: com.amazonaws.services.s3.model.ListObjectsRequest)
    : com.amazonaws.services.s3.model.ObjectListing = ???
  def listObjects(x$1: String,
                  x$2: String): com.amazonaws.services.s3.model.ObjectListing =
    ???
  def listObjects(x$1: String): com.amazonaws.services.s3.model.ObjectListing =
    ???
  def listObjectsV2(x$1: com.amazonaws.services.s3.model.ListObjectsV2Request)
    : com.amazonaws.services.s3.model.ListObjectsV2Result = ???
  def listObjectsV2(
      x$1: String,
      x$2: String): com.amazonaws.services.s3.model.ListObjectsV2Result = ???
  def listObjectsV2(
      x$1: String): com.amazonaws.services.s3.model.ListObjectsV2Result = ???
  def listParts(x$1: com.amazonaws.services.s3.model.ListPartsRequest)
    : com.amazonaws.services.s3.model.PartListing = ???
  def listVersions(x$1: com.amazonaws.services.s3.model.ListVersionsRequest)
    : com.amazonaws.services.s3.model.VersionListing = ???
  def listVersions(
      x$1: String,
      x$2: String,
      x$3: String,
      x$4: String,
      x$5: String,
      x$6: Integer): com.amazonaws.services.s3.model.VersionListing = ???
  def listVersions(
      x$1: String,
      x$2: String): com.amazonaws.services.s3.model.VersionListing = ???
  def putObject(x$1: String,
                x$2: String,
                x$3: String): com.amazonaws.services.s3.model.PutObjectResult =
    ???
  def putObject(x$1: String,
                x$2: String,
                x$3: java.io.InputStream,
                x$4: com.amazonaws.services.s3.model.ObjectMetadata)
    : com.amazonaws.services.s3.model.PutObjectResult = ???
  def putObject(
      x$1: String,
      x$2: String,
      x$3: java.io.File): com.amazonaws.services.s3.model.PutObjectResult = ???
  def putObject(x$1: com.amazonaws.services.s3.model.PutObjectRequest)
    : com.amazonaws.services.s3.model.PutObjectResult = ???
  def restoreObject(x$1: String, x$2: String, x$3: Int): Unit = ???
  def restoreObject(
      x$1: com.amazonaws.services.s3.model.RestoreObjectRequest): Unit = ???
  def restoreObjectV2(x$1: com.amazonaws.services.s3.model.RestoreObjectRequest)
    : com.amazonaws.services.s3.model.RestoreObjectResult = ???
  def selectObjectContent(
      x$1: com.amazonaws.services.s3.model.SelectObjectContentRequest)
    : com.amazonaws.services.s3.model.SelectObjectContentResult = ???
  def setBucketAccelerateConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketAccelerateConfigurationRequest)
    : Unit = ???
  def setBucketAccelerateConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketAccelerateConfiguration)
    : Unit = ???
  def setBucketAcl(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.CannedAccessControlList): Unit = ???
  def setBucketAcl(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.AccessControlList): Unit = ???
  def setBucketAcl(
      x$1: com.amazonaws.services.s3.model.SetBucketAclRequest): Unit = ???
  def setBucketAnalyticsConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketAnalyticsConfigurationRequest)
    : com.amazonaws.services.s3.model.SetBucketAnalyticsConfigurationResult =
    ???
  def setBucketAnalyticsConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.analytics.AnalyticsConfiguration)
    : com.amazonaws.services.s3.model.SetBucketAnalyticsConfigurationResult =
    ???
  def setBucketCrossOriginConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketCrossOriginConfigurationRequest)
    : Unit = ???
  def setBucketCrossOriginConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketCrossOriginConfiguration)
    : Unit = ???
  def setBucketEncryption(
      x$1: com.amazonaws.services.s3.model.SetBucketEncryptionRequest)
    : com.amazonaws.services.s3.model.SetBucketEncryptionResult = ???
  def setBucketInventoryConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketInventoryConfigurationRequest)
    : com.amazonaws.services.s3.model.SetBucketInventoryConfigurationResult =
    ???
  def setBucketInventoryConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.inventory.InventoryConfiguration)
    : com.amazonaws.services.s3.model.SetBucketInventoryConfigurationResult =
    ???
  def setBucketLifecycleConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketLifecycleConfigurationRequest)
    : Unit = ???
  def setBucketLifecycleConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketLifecycleConfiguration): Unit =
    ???
  def setBucketLoggingConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketLoggingConfigurationRequest)
    : Unit = ???
  def setBucketMetricsConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketMetricsConfigurationRequest)
    : com.amazonaws.services.s3.model.SetBucketMetricsConfigurationResult = ???
  def setBucketMetricsConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.metrics.MetricsConfiguration)
    : com.amazonaws.services.s3.model.SetBucketMetricsConfigurationResult = ???
  def setBucketNotificationConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketNotificationConfiguration)
    : Unit = ???
  def setBucketNotificationConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketNotificationConfigurationRequest)
    : Unit = ???
  def setBucketPolicy(
      x$1: com.amazonaws.services.s3.model.SetBucketPolicyRequest): Unit = ???
  def setBucketPolicy(x$1: String, x$2: String): Unit = ???
  def setBucketReplicationConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketReplicationConfigurationRequest)
    : Unit = ???
  def setBucketReplicationConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketReplicationConfiguration)
    : Unit = ???
  def setBucketTaggingConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketTaggingConfigurationRequest)
    : Unit = ???
  def setBucketTaggingConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketTaggingConfiguration): Unit =
    ???
  def setBucketVersioningConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest)
    : Unit = ???
  def setBucketWebsiteConfiguration(
      x$1: com.amazonaws.services.s3.model.SetBucketWebsiteConfigurationRequest)
    : Unit = ???
  def setBucketWebsiteConfiguration(
      x$1: String,
      x$2: com.amazonaws.services.s3.model.BucketWebsiteConfiguration): Unit =
    ???
  def setEndpoint(x$1: String): Unit = ???
  def setObjectAcl(
      x$1: com.amazonaws.services.s3.model.SetObjectAclRequest): Unit = ???
  def setObjectAcl(
      x$1: String,
      x$2: String,
      x$3: String,
      x$4: com.amazonaws.services.s3.model.CannedAccessControlList): Unit = ???
  def setObjectAcl(
      x$1: String,
      x$2: String,
      x$3: String,
      x$4: com.amazonaws.services.s3.model.AccessControlList): Unit = ???
  def setObjectAcl(
      x$1: String,
      x$2: String,
      x$3: com.amazonaws.services.s3.model.CannedAccessControlList): Unit = ???
  def setObjectAcl(
      x$1: String,
      x$2: String,
      x$3: com.amazonaws.services.s3.model.AccessControlList): Unit = ???
  def setObjectRedirectLocation(x$1: String, x$2: String, x$3: String): Unit =
    ???
  def setObjectTagging(
      x$1: com.amazonaws.services.s3.model.SetObjectTaggingRequest)
    : com.amazonaws.services.s3.model.SetObjectTaggingResult = ???
  def setRegion(x$1: com.amazonaws.regions.Region): Unit = ???
  def setS3ClientOptions(x$1: com.amazonaws.services.s3.S3ClientOptions): Unit =
    ???
  def shutdown(): Unit = ???
  def uploadPart(x$1: com.amazonaws.services.s3.model.UploadPartRequest)
    : com.amazonaws.services.s3.model.UploadPartResult = ???
  def waiters(): com.amazonaws.services.s3.waiters.AmazonS3Waiters = ???
}
