package ru.perfolenta.dviz.dto;

import ru.perfolenta.dviz.dto.showcase.*;

import java.util.ArrayList;
import java.util.List;

public class DataShowcaseDto {

    private List<AttributeDto> attributes = new ArrayList<>();
    private List<PersonDto> persons = new ArrayList<>();
    private List<OrgDto> orgs = new ArrayList<>();
    private List<DocumentDto> documents = new ArrayList<>();
    private List<GovDepertmentDto> govDepartment = new ArrayList<>();
    private List<InfSystemDto> infSystem = new ArrayList<>();

    public List<AttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDto> attributes) {
        this.attributes = attributes;
    }

    public List<PersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDto> persons) {
        this.persons = persons;
    }

    public List<OrgDto> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrgDto> orgs) {
        this.orgs = orgs;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDto> documents) {
        this.documents = documents;
    }

    public List<GovDepertmentDto> getGovDepartment() {
        return govDepartment;
    }

    public void setGovDepartment(List<GovDepertmentDto> govDepartment) {
        this.govDepartment = govDepartment;
    }

    public List<InfSystemDto> getInfSystem() {
        return infSystem;
    }

    public void setInfSystem(List<InfSystemDto> infSystem) {
        this.infSystem = infSystem;
    }
}
