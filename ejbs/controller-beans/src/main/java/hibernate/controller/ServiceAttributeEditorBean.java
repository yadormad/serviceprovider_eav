package hibernate.controller;

import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceAttributeTypeObject;
import model.service.obj.ServiceCatalogObject;

import javax.ejb.Stateful;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Stateful(name = "ServiceAttributeEditorEJB")
public class ServiceAttributeEditorBean {
    private ServiceAttributeObject editedServiceAttribute;
    private Map<Integer, ServiceAttributeTypeObject> attributeTypes;

    public void init(Integer attributeId) {
        if (editedServiceAttribute == null || !editedServiceAttribute.getId().equals(attributeId)) {
            editedServiceAttribute = DaoLoader.getDaoFactory().getServiceAttributeDao().getById(attributeId);
            attributeTypes = new HashMap<>();
            for(ServiceAttributeTypeObject attributeType:DaoLoader.getDaoFactory().getServiceAttributeTypeDao().getAll()) {
                attributeTypes.put(attributeType.getId(), attributeType);
            }
        }
    }

    public void refresh() {
        editedServiceAttribute = null;
    }

    public ServiceAttributeObject getEditedServiceAttribute() {
        return editedServiceAttribute;
    }

    public Collection<ServiceAttributeTypeObject> getAllTypes() {
        return attributeTypes.values();
    }

    public void editServiceAttribute(String name, Integer attributeTypeId, boolean isListed) {
        editedServiceAttribute.setName(name);
        editedServiceAttribute.setAttributeType(attributeTypes.get(attributeTypeId));
        editedServiceAttribute.setListed(isListed);
    }

    public void editCatalogValue(Integer catalogId, String value) {
        editedServiceAttribute.getCatalogValueMap().get(catalogId).setValue(value);
    }

    public String getCatalogValue(Integer catalogId) {
        return editedServiceAttribute.getCatalogValueMap().get(catalogId).getValue();
    }

    public void removeCatalogValue(Integer catalogId) {
        editedServiceAttribute.getCatalogValueMap().remove(catalogId);
    }

    public void addCatalogValue(String value) {
        Integer addKey;
        if(editedServiceAttribute.getCatalogValueMap().isEmpty()){
            addKey = 0;
        } else {
            addKey = Collections.min(editedServiceAttribute.getCatalogValueMap().keySet());
        }
        editedServiceAttribute.getCatalogValueMap().put(--addKey, new ServiceCatalogObject(value));
    }

    public void saveServiceAttribute() throws IllegalAccessException, InstantiationException {
        editedServiceAttribute = DaoLoader.getDaoFactory().getServiceAttributeDao().persist(editedServiceAttribute);
    }
}
