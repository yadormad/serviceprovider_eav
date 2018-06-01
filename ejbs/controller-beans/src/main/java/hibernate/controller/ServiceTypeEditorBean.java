package hibernate.controller;

import model.service.obj.ServiceAttributeObject;
import model.service.obj.ServiceTypeObject;

import javax.ejb.Stateful;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Stateful(name = "ServiceTypeEditorEJB")
public class ServiceTypeEditorBean {
    private ServiceTypeObject editedServiceType;
    private Map<Integer, ServiceAttributeObject> allAttributesMap;

    public void init(Integer serviceTypeId) {
        if (editedServiceType == null || !editedServiceType.getId().equals(serviceTypeId)) {
            editedServiceType = DaoLoader.getDaoFactory().getServiceTypeDao().getById(serviceTypeId);
            allAttributesMap = new HashMap<>();
            for(ServiceAttributeObject attributeObject : DaoLoader.getDaoFactory().getServiceAttributeDao().getAll()) {
                allAttributesMap.put(attributeObject.getId(), attributeObject);
                if (!editedServiceType.getAttributeSet().contains(attributeObject)) {
                    allAttributesMap.put(attributeObject.getId(), attributeObject);
                }
            }
        }
    }

    public void refresh() {
        editedServiceType = null;
    }

    public ServiceTypeObject getEditedServiceType() {
        return editedServiceType;
    }

    public void editServiceType(String name, String description) {
        editedServiceType.setName(name);
        editedServiceType.setDescription(description);
    }

    public Collection<ServiceAttributeObject> getAvailableAttributes() {
        Collection<ServiceAttributeObject> availableAttributes = allAttributesMap.values();
        availableAttributes.removeAll(editedServiceType.getAttributeSet());
        return availableAttributes;
    }

    public void addAttributeToServiceType(Integer attributeId) {
        editedServiceType.getAttributeSet().add(allAttributesMap.get(attributeId));
    }

    public void removeAttributeFromServiceType(Integer attributeId) {
        editedServiceType.getAttributeSet().remove(allAttributesMap.get(attributeId));
    }

    public void saveServiceType() throws IllegalAccessException, InstantiationException {
        DaoLoader.getDaoFactory().getServiceTypeDao().persist(editedServiceType);
    }
}
