#import "RNUrlResolver.h"

@implementation RNUrlResolver

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(resolveUrl:(NSURL *)encodedURL
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject)
{
    if (encodedURL == nil) {
        reject(0, @"Unable to handle user activity: No URL provided", nil);
    } else {
        NSURLSession *session = [NSURLSession sharedSession];
        NSURLSessionDataTask *task = [session dataTaskWithURL:encodedURL completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
            if (response == nil || [response URL] == nil) {
                // RCTLogError(@"Unable to handle URL: %@", encodedURL.absoluteString);
                reject(0, @"Unable to handle URL", nil);
            } else {
                NSURL *resolvedURL = [response URL];
                resolve(resolvedURL.absoluteString);
            }
        }];
        [task resume];
        
    }
}

@end
