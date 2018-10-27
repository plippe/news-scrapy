package com.github.plippe.news.scrapy.mocks

import com.amazonaws.services.lambda.AWSLambda

class AwsLambdaMock extends AWSLambda {
  def addPermission(
      x$1: com.amazonaws.services.lambda.model.AddPermissionRequest)
    : com.amazonaws.services.lambda.model.AddPermissionResult = ???
  def createAlias(x$1: com.amazonaws.services.lambda.model.CreateAliasRequest)
    : com.amazonaws.services.lambda.model.CreateAliasResult = ???
  def createEventSourceMapping(
      x$1: com.amazonaws.services.lambda.model.CreateEventSourceMappingRequest)
    : com.amazonaws.services.lambda.model.CreateEventSourceMappingResult = ???
  def createFunction(
      x$1: com.amazonaws.services.lambda.model.CreateFunctionRequest)
    : com.amazonaws.services.lambda.model.CreateFunctionResult = ???
  def deleteAlias(x$1: com.amazonaws.services.lambda.model.DeleteAliasRequest)
    : com.amazonaws.services.lambda.model.DeleteAliasResult = ???
  def deleteEventSourceMapping(
      x$1: com.amazonaws.services.lambda.model.DeleteEventSourceMappingRequest)
    : com.amazonaws.services.lambda.model.DeleteEventSourceMappingResult = ???
  def deleteFunction(
      x$1: com.amazonaws.services.lambda.model.DeleteFunctionRequest)
    : com.amazonaws.services.lambda.model.DeleteFunctionResult = ???
  def deleteFunctionConcurrency(
      x$1: com.amazonaws.services.lambda.model.DeleteFunctionConcurrencyRequest)
    : com.amazonaws.services.lambda.model.DeleteFunctionConcurrencyResult = ???
  def getAccountSettings(
      x$1: com.amazonaws.services.lambda.model.GetAccountSettingsRequest)
    : com.amazonaws.services.lambda.model.GetAccountSettingsResult = ???
  def getAlias(x$1: com.amazonaws.services.lambda.model.GetAliasRequest)
    : com.amazonaws.services.lambda.model.GetAliasResult = ???
  def getCachedResponseMetadata(x$1: com.amazonaws.AmazonWebServiceRequest)
    : com.amazonaws.ResponseMetadata = ???
  def getEventSourceMapping(
      x$1: com.amazonaws.services.lambda.model.GetEventSourceMappingRequest)
    : com.amazonaws.services.lambda.model.GetEventSourceMappingResult = ???
  def getFunction(x$1: com.amazonaws.services.lambda.model.GetFunctionRequest)
    : com.amazonaws.services.lambda.model.GetFunctionResult = ???
  def getFunctionConfiguration(
      x$1: com.amazonaws.services.lambda.model.GetFunctionConfigurationRequest)
    : com.amazonaws.services.lambda.model.GetFunctionConfigurationResult = ???
  def getPolicy(x$1: com.amazonaws.services.lambda.model.GetPolicyRequest)
    : com.amazonaws.services.lambda.model.GetPolicyResult = ???
  def invoke(x$1: com.amazonaws.services.lambda.model.InvokeRequest)
    : com.amazonaws.services.lambda.model.InvokeResult = ???
  @deprecated("", "")
  def invokeAsync(x$1: com.amazonaws.services.lambda.model.InvokeAsyncRequest)
    : com.amazonaws.services.lambda.model.InvokeAsyncResult = ???
  def listAliases(x$1: com.amazonaws.services.lambda.model.ListAliasesRequest)
    : com.amazonaws.services.lambda.model.ListAliasesResult = ???
  def listEventSourceMappings()
    : com.amazonaws.services.lambda.model.ListEventSourceMappingsResult = ???
  def listEventSourceMappings(
      x$1: com.amazonaws.services.lambda.model.ListEventSourceMappingsRequest)
    : com.amazonaws.services.lambda.model.ListEventSourceMappingsResult = ???
  def listFunctions(): com.amazonaws.services.lambda.model.ListFunctionsResult =
    ???
  def listFunctions(
      x$1: com.amazonaws.services.lambda.model.ListFunctionsRequest)
    : com.amazonaws.services.lambda.model.ListFunctionsResult = ???
  def listTags(x$1: com.amazonaws.services.lambda.model.ListTagsRequest)
    : com.amazonaws.services.lambda.model.ListTagsResult = ???
  def listVersionsByFunction(
      x$1: com.amazonaws.services.lambda.model.ListVersionsByFunctionRequest)
    : com.amazonaws.services.lambda.model.ListVersionsByFunctionResult = ???
  def publishVersion(
      x$1: com.amazonaws.services.lambda.model.PublishVersionRequest)
    : com.amazonaws.services.lambda.model.PublishVersionResult = ???
  def putFunctionConcurrency(
      x$1: com.amazonaws.services.lambda.model.PutFunctionConcurrencyRequest)
    : com.amazonaws.services.lambda.model.PutFunctionConcurrencyResult = ???
  def removePermission(
      x$1: com.amazonaws.services.lambda.model.RemovePermissionRequest)
    : com.amazonaws.services.lambda.model.RemovePermissionResult = ???
  def setEndpoint(x$1: String): Unit = ???
  def setRegion(x$1: com.amazonaws.regions.Region): Unit = ???
  def shutdown(): Unit = ???
  def tagResource(x$1: com.amazonaws.services.lambda.model.TagResourceRequest)
    : com.amazonaws.services.lambda.model.TagResourceResult = ???
  def untagResource(
      x$1: com.amazonaws.services.lambda.model.UntagResourceRequest)
    : com.amazonaws.services.lambda.model.UntagResourceResult = ???
  def updateAlias(x$1: com.amazonaws.services.lambda.model.UpdateAliasRequest)
    : com.amazonaws.services.lambda.model.UpdateAliasResult = ???
  def updateEventSourceMapping(
      x$1: com.amazonaws.services.lambda.model.UpdateEventSourceMappingRequest)
    : com.amazonaws.services.lambda.model.UpdateEventSourceMappingResult = ???
  def updateFunctionCode(
      x$1: com.amazonaws.services.lambda.model.UpdateFunctionCodeRequest)
    : com.amazonaws.services.lambda.model.UpdateFunctionCodeResult = ???
  def updateFunctionConfiguration(
      x$1: com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest)
    : com.amazonaws.services.lambda.model.UpdateFunctionConfigurationResult =
    ???
}
