package com.example.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Story_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getAutore(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::autore;
  }-*/;
  
  private static native void setAutore(com.example.shared.Story instance, java.lang.String value) 
  /*-{
    instance.@com.example.shared.Story::autore = value;
  }-*/;
  
  private static native java.lang.String getCategoria(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::categoria;
  }-*/;
  
  private static native void setCategoria(com.example.shared.Story instance, java.lang.String value) 
  /*-{
    instance.@com.example.shared.Story::categoria = value;
  }-*/;
  
  private static native java.lang.String getDescrizione(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::descrizione;
  }-*/;
  
  private static native void setDescrizione(com.example.shared.Story instance, java.lang.String value) 
  /*-{
    instance.@com.example.shared.Story::descrizione = value;
  }-*/;
  
  private static native com.example.shared.Scenario getInizio(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::inizio;
  }-*/;
  
  private static native void setInizio(com.example.shared.Story instance, com.example.shared.Scenario value) 
  /*-{
    instance.@com.example.shared.Story::inizio = value;
  }-*/;
  
  private static native java.util.List getInventario(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::inventario;
  }-*/;
  
  private static native void setInventario(com.example.shared.Story instance, java.util.List value) 
  /*-{
    instance.@com.example.shared.Story::inventario = value;
  }-*/;
  
  private static native java.lang.String getNome(com.example.shared.Story instance) /*-{
    return instance.@com.example.shared.Story::nome;
  }-*/;
  
  private static native void setNome(com.example.shared.Story instance, java.lang.String value) 
  /*-{
    instance.@com.example.shared.Story::nome = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, com.example.shared.Story instance) throws SerializationException {
    setAutore(instance, streamReader.readString());
    setCategoria(instance, streamReader.readString());
    setDescrizione(instance, streamReader.readString());
    setInizio(instance, (com.example.shared.Scenario) streamReader.readObject());
    setInventario(instance, (java.util.List) streamReader.readObject());
    setNome(instance, streamReader.readString());
    
  }
  
  public static com.example.shared.Story instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.example.shared.Story();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.example.shared.Story instance) throws SerializationException {
    streamWriter.writeString(getAutore(instance));
    streamWriter.writeString(getCategoria(instance));
    streamWriter.writeString(getDescrizione(instance));
    streamWriter.writeObject(getInizio(instance));
    streamWriter.writeObject(getInventario(instance));
    streamWriter.writeString(getNome(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.example.shared.Story_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.example.shared.Story_FieldSerializer.deserialize(reader, (com.example.shared.Story)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.example.shared.Story_FieldSerializer.serialize(writer, (com.example.shared.Story)object);
  }
  
}
