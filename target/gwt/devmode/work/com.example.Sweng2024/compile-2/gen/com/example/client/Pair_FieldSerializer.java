package com.example.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Pair_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.Object getA(com.example.client.Pair instance) /*-{
    return instance.@com.example.client.Pair::a;
  }-*/;
  
  private static native void setA(com.example.client.Pair instance, java.lang.Object value) 
  /*-{
    instance.@com.example.client.Pair::a = value;
  }-*/;
  
  private static native java.lang.Object getB(com.example.client.Pair instance) /*-{
    return instance.@com.example.client.Pair::b;
  }-*/;
  
  private static native void setB(com.example.client.Pair instance, java.lang.Object value) 
  /*-{
    instance.@com.example.client.Pair::b = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, com.example.client.Pair instance) throws SerializationException {
    setA(instance, streamReader.readObject());
    setB(instance, streamReader.readObject());
    
  }
  
  public static com.example.client.Pair instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.example.client.Pair();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.client.Pair instance) throws SerializationException {
    streamWriter.writeObject(getA(instance));
    streamWriter.writeObject(getB(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.client.Pair_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.client.Pair_FieldSerializer.deserialize(reader, (com.example.client.Pair)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.client.Pair_FieldSerializer.serialize(writer, (com.example.client.Pair)object);
  }
  
}
