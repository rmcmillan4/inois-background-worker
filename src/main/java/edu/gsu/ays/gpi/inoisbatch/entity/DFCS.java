package edu.gsu.ays.gpi.inoisbatch.entity;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import edu.gsu.ays.gpi.inoisbatch.services.HashService;
import org.apache.commons.lang3.NotImplementedException;
import com.univocity.parsers.annotations.Parsed;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DFCS implements InoisEntity {

    private List<DFCS> batch = new ArrayList<>();

    @Parsed(field = "CD_ADOPT_CNTRY")
    private String cdAdoptCntry;

    @Parsed(field = "CD_ADOPT_COUNTY")
    private String cdAdoptCounty;

    @Parsed(field = "CD_ADOPT_STATE")
    private String cdAdoptState;

    @Parsed(field = "CD_PERSON_CHAR")
    private String cdPersonChar;

    @Parsed(field = "CD_PERSON_DEATH")
    private String cdPersonDeath;

    @Parsed(field = "CD_PERSON_ETHNIC_GROUP")
    private String cdPersonEthnicGroup;

    @Parsed(field = "CD_PERSON_IMMIGRATION_STATUS")
    private String cdPersonImmigrationStatus;

    @Parsed(field = "CD_PERSON_LANGUAGE")
    private String cdPersonILanguage;

    @Parsed(field = "CD_PERSON_LIV_ARR")
    private String cdPersonLivArr;

    @Parsed(field = "CD_PERSON_MARITAL_STATUS")
    private String cdPersonMaritalStatus;

    @Parsed(field = "CD_PERSON_PROOF_CITIZENSHIP")
    private String cdPersonProofCitizenship;

    @Parsed(field = "CD_PERSON_RELIGION")
    private String cdPersonReligion;

    @Parsed(field = "CD_PERSON_SEX")
    private String cdPersonSex;

    @Parsed(field = "CD_PERSON_STATUS")
    private String cdPersonStatus;

    @Parsed(field = "CD_PERSON_SUFFIX")
    private String cdPersonSuffix;

    @Parsed(field = "CD_PERSON_TITLE")
    private String cdPersonTitle;

    @Parsed(field = "CD_SINGLE_MOTHER_FATHER")
    private String cdSingleMotherFather;

    @Parsed(field = "DT_DISSOLUTION")
    private String dtDissolution;

    @Parsed(field = "DT_PERSON_BIRTH")
    private String dtPersonBirth;

    @Parsed(field = "DT_PERSON_DEATH")
    private String dtPersonDeath;

    @Parsed(field = "GENDER")
    private String gender;

    @Parsed(field = "ID_PERSON")
    private String idPerson;

    @Parsed(field = "IND_AUTO_PERS_MERGE")
    private String indAutoPersMerge;

    @Parsed(field = "IND_PERSON_DOB_APPROX")
    private String indPersonDobApprox;

    @Parsed(field = "IND_PREV_ADOPTED")
    private String indPrevAdopted;

    @Parsed(field = "IND_PUBLIC")
    private String indPublic;

    @Parsed(field = "IND_SINGLE_PAR_ADOPT")
    private String indSingleParentAdopt;

    @Parsed(field = "NBR_PERSON_AGE")
    private String nbrPersonAge;

    @Parsed(field = "NBR_PERSON_ID_NUMBER")
    private String nbrPersonIdNumber;

    @Parsed(field = "NBR_PERSON_PHONE")
    private String nbrPersonPhone;

    @Parsed(field = "NM_PERSON_FIRST")
    private String nmPersonFirst;

    @Parsed(field = "NM_PERSON_LAST")
    private String nmPersonLast;

    @Parsed(field = "NM_PERSON_FULL")
    private String nmPersonFull;

    @Parsed(field = "TXT_NAME_OF_ADO_AGENCY")
    private String txtNameOfAdoAgency;

    @Parsed(field = "TXT_PERSON_OCCUPATION")
    private String txtPersonOccupation;

    @Parsed(field = "TXT_PERSON_OTHER_RELATIONSHIPS")
    private String txtPersonOtherRelationships;

    @Parsed(field = "ID_PERSON_hash")
    private String idPersonHash;

    @Parsed(field = "NBR_PERSON_ID_NUMBER_hash")
    private String nbrPersonIdNumberHash;

    private JdbcTemplate jdbcTemplate;

    private String user;

    private String batchId;



    public DFCS(JdbcTemplate jdbcTemplate, BatchHeaderQueue batchHeaderQueue){
        this.jdbcTemplate = jdbcTemplate;
        this.batchId = batchHeaderQueue.getBatchIdentifier();
        this.user = batchHeaderQueue.getCreatedBy();
    }

    public DFCS(){}


    public String getNbrPersonIdNumberHash() { return nbrPersonIdNumberHash; }
    public void setNbrPersonIdNumberHash(String nbrPersonIdNumberHash) { this.nbrPersonIdNumberHash = nbrPersonIdNumberHash; }

    public String getIdPersonHash() { return idPersonHash; }
    public void setIdPersonHash(String idPersonHash) { this.idPersonHash = idPersonHash; }

    public String getTxtPersonOtherRelationships() { return txtPersonOtherRelationships; }
    public void setTxtPersonOtherRelationships(String txtPersonOtherRelationships) { this.txtPersonOtherRelationships = txtPersonOtherRelationships; }

    public String getTxtPersonOccupation() { return txtPersonOccupation; }
    public void setTxtPersonOccupation(String txtPersonOccupation) { this.txtPersonOccupation = txtPersonOccupation; }

    public String getTxtNameOfAdoAgency() { return txtNameOfAdoAgency; }
    public void setTxtNameOfAdoAgency(String txtNameOfAdoAgency) { this.txtNameOfAdoAgency = txtNameOfAdoAgency; }

    public String getNmPersonLast() { return nmPersonLast; }
    public void setNmPersonLast(String nmPersonLast) { this.nmPersonLast = nmPersonLast; }

    public String getNmPersonFirst() { return nmPersonFirst; }
    public void setNmPersonFirst(String nmPersonFirst) { this.nmPersonFirst = nmPersonFirst; }

    public String getNbrPersonPhone() { return nbrPersonPhone; }
    public void setNbrPersonPhone(String nbrPersonPhone) { this.nbrPersonPhone = nbrPersonPhone; }

    public String getNbrPersonIdNumber() { return nbrPersonIdNumber; }
    public void setNbrPersonIdNumber(String nbrPersonIdNumber) { this.nbrPersonIdNumber = nbrPersonIdNumber; }

    public String getNbrPersonAge() { return nbrPersonAge; }
    public void setNbrPersonAge(String nbrPersonAge) { this.nbrPersonAge = nbrPersonAge; }

    public String getIndSingleParentAdopt() { return indSingleParentAdopt; }
    public void setIndSingleParentAdopt(String indSingleParentAdopt) { this.indSingleParentAdopt = indSingleParentAdopt; }

    public String getIndPublic() { return indPublic; }
    public void setIndPublic(String indPublic) { this.indPublic = indPublic; }

    public String getIndPrevAdopted() { return indPrevAdopted; }
    public void setIndPrevAdopted(String indPrevAdopted) { this.indPrevAdopted = indPrevAdopted; }

    public String getIndPersonDobApprox() { return indPersonDobApprox; }
    public void setIndPersonDobApprox(String indPersonDobApprox) { this.indPersonDobApprox = indPersonDobApprox; }

    public String getIndAutoPersMerge() { return indAutoPersMerge; }
    public void setIndAutoPersMerge(String indAutoPersMerge) { this.indAutoPersMerge = indAutoPersMerge; }

    public String getNmPersonFull() { return nmPersonFull; }
    public void setNmPersonFull(String nmPersonFull) { this.nmPersonFull = nmPersonFull; }

    public String getIdPerson() { return idPerson; }
    public void setIdPerson(String idPerson) { this.idPerson = idPerson; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDtPersonDeath() { return dtPersonDeath; }
    public void setDtPersonDeath(String dtPersonDeath) { this.dtPersonDeath = dtPersonDeath; }

    public String getDtPersonBirth() { return dtPersonBirth; }
    public void setDtPersonBirth(String dtPersonBirth) { this.dtPersonBirth = dtPersonBirth; }

    public String getDtDissolution() { return dtDissolution; }
    public void setDtDissolution(String dtDissolution) { this.dtDissolution = dtDissolution; }

    public String getCdSingleMotherFather() { return cdSingleMotherFather; }
    public void setCdSingleMotherFather(String cdSingleMotherFather) { this.cdSingleMotherFather = cdSingleMotherFather; }

    public String getCdPersonTitle() { return cdPersonTitle; }
    public void setCdPersonTitle(String cdPersonTitle) { this.cdPersonTitle = cdPersonTitle; }

    public String getCdPersonSuffix() { return cdPersonSuffix; }
    public void setCdPersonSuffix(String cdPersonSuffix) { this.cdPersonSuffix = cdPersonSuffix; }

    public String getCdPersonStatus() { return cdPersonStatus; }
    public void setCdPersonStatus(String cdPersonStatus) { this.cdPersonStatus = cdPersonStatus; }

    public String getCdPersonSex() { return cdPersonSex; }
    public void setCdPersonSex(String cdPersonSex) { this.cdPersonSex = cdPersonSex; }

    public String getCdPersonReligion() { return cdPersonReligion; }
    public void setCdPersonReligion(String cdPersonReligion) { this.cdPersonReligion = cdPersonReligion; }

    public String getCdPersonProofCitizenship() { return cdPersonProofCitizenship; }
    public void setCdPersonProofCitizenship(String cdPersonProofCitizenship) { this.cdPersonProofCitizenship = cdPersonProofCitizenship; }

    public String getCdPersonMaritalStatus() { return cdPersonMaritalStatus; }
    public void setCdPersonMaritalStatus(String cdPersonMaritalStatus) { this.cdPersonMaritalStatus = cdPersonMaritalStatus; }

    public String getCdPersonLivArr() { return cdPersonLivArr; }
    public void setCdPersonLivArr(String cdPersonLivArr) { this.cdPersonLivArr = cdPersonLivArr; }

    public String getCdPersonILanguage() { return cdPersonILanguage; }
    public void setCdPersonILanguage(String cdPersonILanguage) { this.cdPersonILanguage = cdPersonILanguage; }

    public String getCdPersonImmigrationStatus() { return cdPersonImmigrationStatus; }
    public void setCdPersonImmigrationStatus(String cdPersonImmigrationStatus) { this.cdPersonImmigrationStatus = cdPersonImmigrationStatus; }

    public String getCdPersonEthnicGroup() { return cdPersonEthnicGroup; }
    public void setCdPersonEthnicGroup(String cdPersonEthnicGroup) { this.cdPersonEthnicGroup = cdPersonEthnicGroup; }

    public String getCdPersonDeath() { return cdPersonDeath; }
    public void setCdPersonDeath(String cdPersonDeath) { this.cdPersonDeath = cdPersonDeath; }

    public String getCdPersonChar() { return cdPersonChar; }
    public void setCdPersonChar(String cdPersonChar) { this.cdPersonChar = cdPersonChar; }

    public String getCdAdoptCntry() { return cdAdoptCntry; }
    public void setCdAdoptCntry(String cdAdoptCntry) { this.cdAdoptCntry = cdAdoptCntry; }

    public String getCdAdoptCounty() { return cdAdoptCounty; }
    public void setCdAdoptCounty(String cdAdoptCounty) { this.cdAdoptCounty = cdAdoptCounty; }

    public String getCdAdoptState() { return cdAdoptState; }
    public void setCdAdoptState(String cdAdoptState) { this.cdAdoptState = cdAdoptState; }

    public List<DFCS> getBatch() { return batch; }
    public void setBatch(List<DFCS> batch) { this.batch = batch; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }


    public void hash(String saltKey){
        idPersonHash = HashService.hashValue(idPersonHash, saltKey);
        nbrPersonIdNumberHash = HashService.hashValue(nbrPersonIdNumberHash, saltKey);
    }

    public void readBatch(String csv) throws IOException {

        try (Reader reader = new StringReader(csv)){
            BeanListProcessor<DFCS> rowProcessor = new BeanListProcessor<DFCS>(DFCS.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.getFormat().setLineSeparator("\n");
            parserSettings.setRowProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(reader);

            List<DFCS> beans = rowProcessor.getBeans();
            this.batch = beans;

        }
    }

    @Transactional
    public void writeBatch(){

        int[][] updateCounts = jdbcTemplate.batchUpdate(
                "INSERT INTO dbo.dfcs (CREATED, CREATED_BY, UPDATED, UPDATED_BY, ID_PERSON_hash, NBR_PERSON_ID_NUMBER_hash" +
                        ") values(CURRENT_TIMESTAMP,?, CURRENT_TIMESTAMP, ?, ?, ?)",
                this.batch,
                50, //batch size
                new ParameterizedPreparedStatementSetter<DFCS>() {
                    public void setValues(PreparedStatement ps, DFCS entity) throws SQLException {
                        ps.setString(1, user);
                        ps.setString(2, user);
                        ps.setString(3, entity.getIdPersonHash());
                        ps.setString(4, entity.getNbrPersonIdNumberHash());
                    }
                });
    }

    public List<DFCS> retrieveBatch(){
        return batch;
    }

    @Override
    public String toString() {
        return "DFCS Record{" +
                "CD_ADOPT_CNTRY='" + cdAdoptCntry + "', " +
                "CD_ADOPT_COUNTY='" + cdAdoptCounty + "', " +
                "CD_PERSON_CHAR='" + cdPersonChar + "', " +
                "CD_PERSON_DEATH='" + cdPersonDeath + "', " +
                "CD_ADOPT_STATE='" + cdAdoptState + "', " +
                "CD_PERSON_ETHNIC_GROUP='" + cdPersonEthnicGroup + "', " +
                "CD_PERSON_IMMIGRATION_STATUS='" + cdPersonImmigrationStatus + "', " +
                "CD_PERSON_LANGUAGE='" + cdPersonILanguage + "', " +
                "CD_PERSON_LIV_ARR='" + cdPersonLivArr + "', " +
                "CD_PERSON_MARITAL_STATUS='" + cdPersonMaritalStatus + "', " +
                "CD_PERSON_PROOF_CITIZENSHIP='" + cdPersonProofCitizenship + "', " +
                "CD_PERSON_RELIGION='" + cdPersonReligion + "', " +
                "CD_PERSON_SEX='" + cdPersonSex + "', " +
                "CD_PERSON_STATUS='" + cdPersonStatus + "', " +
                "CD_PERSON_SUFFIX='" + cdPersonSuffix + "', " +
                "CD_PERSON_TITLE='" + cdPersonTitle + "', " +
                "CD_SINGLE_MOTHER_FATHER='" + cdSingleMotherFather + "', " +
                "DT_DISSOLUTION='" + dtDissolution + "', " +
                "DT_PERSON_BIRTH='" + dtPersonBirth + "', " +
                "DT_PERSON_DEATH='" + dtPersonDeath + "', " +
                "GENDER='" + gender + "', " +
                "ID_PERSON='" + idPerson + "', " +
                "IND_AUTO_PERS_MERGE='" + indAutoPersMerge + "', " +
                "IND_PERSON_DOB_APPROX='" + indPersonDobApprox + "', " +
                "IND_PREV_ADOPTED='" + indPrevAdopted + "', " +
                "IND_PUBLIC='" + indPublic + "', " +
                "IND_SINGLE_PAR_ADOPT='" + indSingleParentAdopt + "', " +
                "NBR_PERSON_AGE='" + nbrPersonAge + "', " +
                "NBR_PERSON_ID_NUMBER='" + nbrPersonIdNumber + "', " +
                "NBR_PERSON_PHONE='" + nbrPersonPhone + "', " +
                "NM_PERSON_FIRST='" + nmPersonFirst + "', " +
                "NM_PERSON_LAST='" + nmPersonLast + "', " +
                "NM_PERSON_FULL='" + nmPersonFull + "', " +
                "TXT_NAME_OF_ADO_AGENCY='" + txtNameOfAdoAgency + "', " +
                "TXT_PERSON_OCCUPATION='" + txtPersonOccupation + "', " +
                "TXT_PERSON_OTHER_RELATIONSHIPS='" + txtPersonOtherRelationships + "', " +
                "ID_PERSON_hash='" + idPersonHash + "', " +
                "NBR_PERSON_ID_NUMBER_hash='" + nbrPersonIdNumberHash +
                "}";

    }
}
