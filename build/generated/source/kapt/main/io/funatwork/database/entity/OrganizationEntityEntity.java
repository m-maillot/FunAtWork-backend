// Generated file do not edit, generated by io.requery.processor.EntityProcessor
package io.funatwork.database.entity;

import io.requery.Persistable;
import io.requery.meta.AttributeBuilder;
import io.requery.meta.AttributeDelegate;
import io.requery.meta.Type;
import io.requery.meta.TypeBuilder;
import io.requery.proxy.EntityProxy;
import io.requery.proxy.IntProperty;
import io.requery.proxy.Property;
import io.requery.proxy.PropertyState;
import io.requery.util.function.Function;
import io.requery.util.function.Supplier;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.Generated;

@Generated("io.requery.processor.EntityProcessor")
public class OrganizationEntityEntity implements OrganizationEntity, Persistable {
    public static final AttributeDelegate<OrganizationEntityEntity, Integer> ID = new AttributeDelegate(
    new AttributeBuilder<OrganizationEntityEntity, Integer>("id", int.class)
    .setProperty(new IntProperty<OrganizationEntityEntity>() {
        @Override
        public Integer get(OrganizationEntityEntity entity) {
            return entity.id;
        }

        @Override
        public void set(OrganizationEntityEntity entity, Integer value) {
            entity.id = value;
        }

        @Override
        public int getInt(OrganizationEntityEntity entity) {
            return entity.id;
        }

        @Override
        public void setInt(OrganizationEntityEntity entity, int value) {
            entity.id = value;
        }
    })
    .setPropertyName("getId")
    .setPropertyState(new Property<OrganizationEntityEntity, PropertyState>() {
        @Override
        public PropertyState get(OrganizationEntityEntity entity) {
            return entity.$id_state;
        }

        @Override
        public void set(OrganizationEntityEntity entity, PropertyState value) {
            entity.$id_state = value;
        }
    })
    .setKey(true)
    .setGenerated(true)
    .setReadOnly(true)
    .setLazy(false)
    .setNullable(false)
    .setUnique(false)
    .build());

    public static final AttributeDelegate<OrganizationEntityEntity, String> NAME = new AttributeDelegate(
    new AttributeBuilder<OrganizationEntityEntity, String>("name", String.class)
    .setProperty(new Property<OrganizationEntityEntity, String>() {
        @Override
        public String get(OrganizationEntityEntity entity) {
            return entity.name;
        }

        @Override
        public void set(OrganizationEntityEntity entity, String value) {
            entity.name = value;
        }
    })
    .setPropertyName("getName")
    .setPropertyState(new Property<OrganizationEntityEntity, PropertyState>() {
        @Override
        public PropertyState get(OrganizationEntityEntity entity) {
            return entity.$name_state;
        }

        @Override
        public void set(OrganizationEntityEntity entity, PropertyState value) {
            entity.$name_state = value;
        }
    })
    .setGenerated(false)
    .setReadOnly(false)
    .setLazy(false)
    .setNullable(true)
    .setUnique(false)
    .build());

    public static final Type<OrganizationEntityEntity> $TYPE = new TypeBuilder<OrganizationEntityEntity>(OrganizationEntityEntity.class, "OrganizationEntity")
    .setBaseType(OrganizationEntity.class)
    .setCacheable(true)
    .setImmutable(false)
    .setReadOnly(false)
    .setStateless(false)
    .setView(false)
    .setFactory(new Supplier<OrganizationEntityEntity>() {
        @Override
        public OrganizationEntityEntity get() {
            return new OrganizationEntityEntity();
        }
    })
    .setProxyProvider(new Function<OrganizationEntityEntity, EntityProxy<OrganizationEntityEntity>>() {
        @Override
        public EntityProxy<OrganizationEntityEntity> apply(OrganizationEntityEntity entity) {
            return entity.$proxy;
        }
    })
    .addAttribute(ID)
    .addAttribute(NAME)
    .build();

    private PropertyState $id_state;

    private PropertyState $name_state;

    private int id;

    private String name;

    private final transient EntityProxy<OrganizationEntityEntity> $proxy = new EntityProxy<OrganizationEntityEntity>(this, $TYPE);

    public OrganizationEntityEntity() {
    }

    @Override
    public int getId() {
        return $proxy.get(ID);
    }

    @Override
    public String getName() {
        return $proxy.get(NAME);
    }

    public void setName(String name) {
        $proxy.set(NAME, name);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OrganizationEntityEntity && ((OrganizationEntityEntity)obj).$proxy.equals(this.$proxy);
    }

    @Override
    public int hashCode() {
        return $proxy.hashCode();
    }

    @Override
    public String toString() {
        return $proxy.toString();
    }
}