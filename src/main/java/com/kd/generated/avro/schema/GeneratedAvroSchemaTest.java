/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.kd.generated.avro.schema;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

import java.io.Serializable;

/** Here you can give more details about the use of this schema */
@org.apache.avro.specific.AvroGenerated
public class GeneratedAvroSchemaTest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord, Serializable {
  private static final long serialVersionUID = 1957890498699037635L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"GeneratedAvroSchemaTest\",\"namespace\":\"com.kd.generated.avro.schema\",\"doc\":\"Here you can give more details about the use of this schema\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"first_name\",\"type\":\"string\"},{\"name\":\"last_name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"long\"},{\"name\":\"birth_date\",\"type\":\"string\"},{\"name\":\"address\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"address\",\"fields\":[{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"living_cost\",\"type\":\"long\"}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<GeneratedAvroSchemaTest> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<GeneratedAvroSchemaTest> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<GeneratedAvroSchemaTest> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<GeneratedAvroSchemaTest> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<GeneratedAvroSchemaTest> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this GeneratedAvroSchemaTest to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a GeneratedAvroSchemaTest from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a GeneratedAvroSchemaTest instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static GeneratedAvroSchemaTest fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long id;
  private java.lang.CharSequence first_name;
  private java.lang.CharSequence last_name;
  private long age;
  private java.lang.CharSequence birth_date;
  private java.util.List<com.kd.generated.avro.schema.address> address;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public GeneratedAvroSchemaTest() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param first_name The new value for first_name
   * @param last_name The new value for last_name
   * @param age The new value for age
   * @param birth_date The new value for birth_date
   * @param address The new value for address
   */
  public GeneratedAvroSchemaTest(java.lang.Long id, java.lang.CharSequence first_name, java.lang.CharSequence last_name, java.lang.Long age, java.lang.CharSequence birth_date, java.util.List<com.kd.generated.avro.schema.address> address) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.age = age;
    this.birth_date = birth_date;
    this.address = address;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return first_name;
    case 2: return last_name;
    case 3: return age;
    case 4: return birth_date;
    case 5: return address;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: first_name = (java.lang.CharSequence)value$; break;
    case 2: last_name = (java.lang.CharSequence)value$; break;
    case 3: age = (java.lang.Long)value$; break;
    case 4: birth_date = (java.lang.CharSequence)value$; break;
    case 5: address = (java.util.List<com.kd.generated.avro.schema.address>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public long getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'first_name' field.
   * @return The value of the 'first_name' field.
   */
  public java.lang.CharSequence getFirstName() {
    return first_name;
  }


  /**
   * Sets the value of the 'first_name' field.
   * @param value the value to set.
   */
  public void setFirstName(java.lang.CharSequence value) {
    this.first_name = value;
  }

  /**
   * Gets the value of the 'last_name' field.
   * @return The value of the 'last_name' field.
   */
  public java.lang.CharSequence getLastName() {
    return last_name;
  }


  /**
   * Sets the value of the 'last_name' field.
   * @param value the value to set.
   */
  public void setLastName(java.lang.CharSequence value) {
    this.last_name = value;
  }

  /**
   * Gets the value of the 'age' field.
   * @return The value of the 'age' field.
   */
  public long getAge() {
    return age;
  }


  /**
   * Sets the value of the 'age' field.
   * @param value the value to set.
   */
  public void setAge(long value) {
    this.age = value;
  }

  /**
   * Gets the value of the 'birth_date' field.
   * @return The value of the 'birth_date' field.
   */
  public java.lang.CharSequence getBirthDate() {
    return birth_date;
  }


  /**
   * Sets the value of the 'birth_date' field.
   * @param value the value to set.
   */
  public void setBirthDate(java.lang.CharSequence value) {
    this.birth_date = value;
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public java.util.List<com.kd.generated.avro.schema.address> getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(java.util.List<com.kd.generated.avro.schema.address> value) {
    this.address = value;
  }

  /**
   * Creates a new GeneratedAvroSchemaTest RecordBuilder.
   * @return A new GeneratedAvroSchemaTest RecordBuilder
   */
  public static com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder newBuilder() {
    return new com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder();
  }

  /**
   * Creates a new GeneratedAvroSchemaTest RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new GeneratedAvroSchemaTest RecordBuilder
   */
  public static com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder newBuilder(com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder other) {
    if (other == null) {
      return new com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder();
    } else {
      return new com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder(other);
    }
  }

  /**
   * Creates a new GeneratedAvroSchemaTest RecordBuilder by copying an existing GeneratedAvroSchemaTest instance.
   * @param other The existing instance to copy.
   * @return A new GeneratedAvroSchemaTest RecordBuilder
   */
  public static com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder newBuilder(com.kd.generated.avro.schema.GeneratedAvroSchemaTest other) {
    if (other == null) {
      return new com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder();
    } else {
      return new com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder(other);
    }
  }

  /**
   * RecordBuilder for GeneratedAvroSchemaTest instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<GeneratedAvroSchemaTest>
    implements org.apache.avro.data.RecordBuilder<GeneratedAvroSchemaTest> {

    private long id;
    private java.lang.CharSequence first_name;
    private java.lang.CharSequence last_name;
    private long age;
    private java.lang.CharSequence birth_date;
    private java.util.List<com.kd.generated.avro.schema.address> address;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.first_name)) {
        this.first_name = data().deepCopy(fields()[1].schema(), other.first_name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.birth_date)) {
        this.birth_date = data().deepCopy(fields()[4].schema(), other.birth_date);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.address)) {
        this.address = data().deepCopy(fields()[5].schema(), other.address);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing GeneratedAvroSchemaTest instance
     * @param other The existing instance to copy.
     */
    private Builder(com.kd.generated.avro.schema.GeneratedAvroSchemaTest other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.first_name)) {
        this.first_name = data().deepCopy(fields()[1].schema(), other.first_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.birth_date)) {
        this.birth_date = data().deepCopy(fields()[4].schema(), other.birth_date);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.address)) {
        this.address = data().deepCopy(fields()[5].schema(), other.address);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public long getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'first_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getFirstName() {
      return first_name;
    }


    /**
      * Sets the value of the 'first_name' field.
      * @param value The value of 'first_name'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setFirstName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.first_name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'first_name' field has been set.
      * @return True if the 'first_name' field has been set, false otherwise.
      */
    public boolean hasFirstName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'first_name' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearFirstName() {
      first_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'last_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getLastName() {
      return last_name;
    }


    /**
      * Sets the value of the 'last_name' field.
      * @param value The value of 'last_name'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setLastName(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.last_name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'last_name' field has been set.
      * @return True if the 'last_name' field has been set, false otherwise.
      */
    public boolean hasLastName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'last_name' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearLastName() {
      last_name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * @return The value.
      */
    public long getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * @param value The value of 'age'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setAge(long value) {
      validate(fields()[3], value);
      this.age = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'age' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearAge() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'birth_date' field.
      * @return The value.
      */
    public java.lang.CharSequence getBirthDate() {
      return birth_date;
    }


    /**
      * Sets the value of the 'birth_date' field.
      * @param value The value of 'birth_date'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setBirthDate(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.birth_date = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'birth_date' field has been set.
      * @return True if the 'birth_date' field has been set, false otherwise.
      */
    public boolean hasBirthDate() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'birth_date' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearBirthDate() {
      birth_date = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'address' field.
      * @return The value.
      */
    public java.util.List<com.kd.generated.avro.schema.address> getAddress() {
      return address;
    }


    /**
      * Sets the value of the 'address' field.
      * @param value The value of 'address'.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder setAddress(java.util.List<com.kd.generated.avro.schema.address> value) {
      validate(fields()[5], value);
      this.address = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public com.kd.generated.avro.schema.GeneratedAvroSchemaTest.Builder clearAddress() {
      address = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public GeneratedAvroSchemaTest build() {
      try {
        GeneratedAvroSchemaTest record = new GeneratedAvroSchemaTest();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.first_name = fieldSetFlags()[1] ? this.first_name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.last_name = fieldSetFlags()[2] ? this.last_name : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.age = fieldSetFlags()[3] ? this.age : (java.lang.Long) defaultValue(fields()[3]);
        record.birth_date = fieldSetFlags()[4] ? this.birth_date : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.address = fieldSetFlags()[5] ? this.address : (java.util.List<com.kd.generated.avro.schema.address>) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<GeneratedAvroSchemaTest>
    WRITER$ = (org.apache.avro.io.DatumWriter<GeneratedAvroSchemaTest>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<GeneratedAvroSchemaTest>
    READER$ = (org.apache.avro.io.DatumReader<GeneratedAvroSchemaTest>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.id);

    out.writeString(this.first_name);

    out.writeString(this.last_name);

    out.writeLong(this.age);

    out.writeString(this.birth_date);

    long size0 = this.address.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (com.kd.generated.avro.schema.address e0: this.address) {
      actualSize0++;
      out.startItem();
      e0.customEncode(out);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readLong();

      this.first_name = in.readString(this.first_name instanceof Utf8 ? (Utf8)this.first_name : null);

      this.last_name = in.readString(this.last_name instanceof Utf8 ? (Utf8)this.last_name : null);

      this.age = in.readLong();

      this.birth_date = in.readString(this.birth_date instanceof Utf8 ? (Utf8)this.birth_date : null);

      long size0 = in.readArrayStart();
      java.util.List<com.kd.generated.avro.schema.address> a0 = this.address;
      if (a0 == null) {
        a0 = new SpecificData.Array<com.kd.generated.avro.schema.address>((int)size0, SCHEMA$.getField("address").schema());
        this.address = a0;
      } else a0.clear();
      SpecificData.Array<com.kd.generated.avro.schema.address> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.kd.generated.avro.schema.address>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          com.kd.generated.avro.schema.address e0 = (ga0 != null ? ga0.peek() : null);
          if (e0 == null) {
            e0 = new com.kd.generated.avro.schema.address();
          }
          e0.customDecode(in);
          a0.add(e0);
        }
      }

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readLong();
          break;

        case 1:
          this.first_name = in.readString(this.first_name instanceof Utf8 ? (Utf8)this.first_name : null);
          break;

        case 2:
          this.last_name = in.readString(this.last_name instanceof Utf8 ? (Utf8)this.last_name : null);
          break;

        case 3:
          this.age = in.readLong();
          break;

        case 4:
          this.birth_date = in.readString(this.birth_date instanceof Utf8 ? (Utf8)this.birth_date : null);
          break;

        case 5:
          long size0 = in.readArrayStart();
          java.util.List<com.kd.generated.avro.schema.address> a0 = this.address;
          if (a0 == null) {
            a0 = new SpecificData.Array<com.kd.generated.avro.schema.address>((int)size0, SCHEMA$.getField("address").schema());
            this.address = a0;
          } else a0.clear();
          SpecificData.Array<com.kd.generated.avro.schema.address> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.kd.generated.avro.schema.address>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              com.kd.generated.avro.schema.address e0 = (ga0 != null ? ga0.peek() : null);
              if (e0 == null) {
                e0 = new com.kd.generated.avro.schema.address();
              }
              e0.customDecode(in);
              a0.add(e0);
            }
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










