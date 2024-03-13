package com.example.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.impl.TypeHandler;
import java.util.HashMap;
import java.util.Map;
import com.google.gwt.core.client.GwtScriptOnly;

public class AddStoryService_TypeSerializer extends com.google.gwt.user.client.rpc.impl.SerializerBase {
  private static final MethodMap methodMapNative;
  private static final JsArrayString signatureMapNative;
  
  static {
    methodMapNative = loadMethodsNative();
    signatureMapNative = loadSignaturesNative();
  }
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native MethodMap loadMethodsNative() /*-{
    var result = {};
    result["com.example.shared.Scenario/1461790730"] = [
        ,
        ,
        @com.example.shared.Scenario_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/example/shared/Scenario;)
      ];
    
    result["com.example.shared.Story/1649275385"] = [
        ,
        ,
        @com.example.shared.Story_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/example/shared/Story;)
      ];
    
    result["com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533"] = [
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;)
      ];
    
    result["com.google.gwt.user.client.rpc.RpcTokenException/2345075298"] = [
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/RpcTokenException;),
      ];
    
    result["com.google.gwt.user.client.rpc.XsrfToken/4254043109"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/XsrfToken;)
      ];
    
    result["java.lang.Integer/3438268394"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.lang.Integer_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/Integer;)
      ];
    
    result["java.lang.String/2004016611"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/String;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/String;)
      ];
    
    result["java.util.Collections$EmptyMap/4174664486"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.Collections.EmptyMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/Map;)
      ];
    
    result["java.util.HashMap/1797211028"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.HashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/HashMap;)
      ];
    
    result["java.util.IdentityHashMap/1839153020"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.IdentityHashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/IdentityHashMap;)
      ];
    
    result["java.util.LinkedHashMap/3008245022"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.LinkedHashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/LinkedHashMap;)
      ];
    
    result["java.util.TreeMap/1493889780"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/TreeMap;)
      ];
    
    return result;
  }-*/;
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native JsArrayString loadSignaturesNative() /*-{
    var result = [];
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.example.shared.Scenario::class)] = "com.example.shared.Scenario/1461790730";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.example.shared.Story::class)] = "com.example.shared.Story/1649275385";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException::class)] = "com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.RpcTokenException::class)] = "com.google.gwt.user.client.rpc.RpcTokenException/2345075298";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.XsrfToken::class)] = "com.google.gwt.user.client.rpc.XsrfToken/4254043109";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.lang.Integer::class)] = "java.lang.Integer/3438268394";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.lang.String::class)] = "java.lang.String/2004016611";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.Collections.EmptyMap::class)] = "java.util.Collections$EmptyMap/4174664486";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.HashMap::class)] = "java.util.HashMap/1797211028";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.IdentityHashMap::class)] = "java.util.IdentityHashMap/1839153020";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.LinkedHashMap::class)] = "java.util.LinkedHashMap/3008245022";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.TreeMap::class)] = "java.util.TreeMap/1493889780";
    return result;
  }-*/;
  
  public AddStoryService_TypeSerializer() {
    super(null, methodMapNative, null, signatureMapNative);
  }
  
}
