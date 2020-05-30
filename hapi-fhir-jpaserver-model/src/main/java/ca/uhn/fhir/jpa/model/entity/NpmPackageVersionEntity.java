package ca.uhn.fhir.jpa.model.entity;

import ca.uhn.fhir.context.FhirVersionEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@Entity()
@Table(name = "NPM_PACKAGE_VER", uniqueConstraints = {
}, indexes = {
	@Index(name = "IDX_PACKVER", columnList = "PACKAGE_ID,VERSION_ID")
})
public class NpmPackageVersionEntity {

	public static final int VERSION_ID_LENGTH = 200;
	public static final int FHIR_VERSION_LENGTH = 10;

	@SequenceGenerator(name = "SEQ_NPM_PACKVER", sequenceName = "SEQ_NPM_PACKVER")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_NPM_PACKVER")
	@Id
	@Column(name = "PID")
	private Long myId;
	@Column(name = "PACKAGE_ID", length = NpmPackageEntity.PACKAGE_ID_LENGTH, nullable = false)
	private String myPackageId;
	@Column(name = "VERSION_ID", length = NpmPackageVersionEntity.VERSION_ID_LENGTH, nullable = false)
	private String myVersionId;
	@ManyToOne
	@JoinColumn(name = "PACKAGE_PID", nullable = false, foreignKey = @ForeignKey(name = "FK_NPM_PKV_PKG"))
	private NpmPackageEntity myPackage;
	@OneToOne
	@JoinColumn(name = "BINARY_RES_ID", referencedColumnName = "RES_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_NPM_PKV_RESID"))
	private ResourceTable myPackageBinary;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SAVED_TIME", nullable = false)
	private Date mySavedTime;
	@Column(name = "DESC", nullable = false, length = 500)
	private String myDescription;
	@Column(name = "CURRENT_VERSION", nullable = false)
	private boolean myCurrentVersion;
	@Column(name = "FHIR_VERSION_ID", length = NpmPackageVersionEntity.FHIR_VERSION_LENGTH, nullable = false)
	private String myFhirVersionId;
	@Enumerated(EnumType.STRING)
	@Column(name = "FHIR_VERSION", length = NpmPackageVersionEntity.FHIR_VERSION_LENGTH, nullable = false)
	private FhirVersionEnum myFhirVersion;
	@Column(name = "PACKAGE_SIZE_BYTES", nullable = false)
	private long myPackageSizeBytes;
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	@Column(name = "UPDATED_TIME", nullable = false)
	private Date myVersion;
	@Column(name = "PACKAGE_NAME", nullable = true, length = 200)
	private String myName;

	public long getPackageSizeBytes() {
		return myPackageSizeBytes;
	}

	public void setPackageSizeBytes(long thePackageSizeBytes) {
		myPackageSizeBytes = thePackageSizeBytes;
	}

	public boolean isCurrentVersion() {
		return myCurrentVersion;
	}

	public void setCurrentVersion(boolean theCurrentVersion) {
		myCurrentVersion = theCurrentVersion;
	}

	public String getPackageId() {
		return myPackageId;
	}

	public void setPackageId(String thePackageId) {
		myPackageId = thePackageId;
	}

	public String getVersionId() {
		return myVersionId;
	}

	public void setVersionId(String theVersionId) {
		myVersionId = theVersionId;
	}

	public String getFhirVersionId() {
		return myFhirVersionId;
	}

	public void setFhirVersionId(String theFhirVersionId) {
		myFhirVersionId = theFhirVersionId;
	}

	public FhirVersionEnum getFhirVersion() {
		return myFhirVersion;
	}

	public void setFhirVersion(FhirVersionEnum theFhirVersion) {
		myFhirVersion = theFhirVersion;
	}

	public NpmPackageEntity getPackage() {
		return myPackage;
	}

	public void setPackage(NpmPackageEntity thePackage) {
		myPackage = thePackage;
	}

	public ResourceTable getPackageBinary() {
		return myPackageBinary;
	}

	public void setPackageBinary(ResourceTable thePackageBinary) {
		myPackageBinary = thePackageBinary;
	}

	public void setSavedTime(Date theSavedTime) {
		mySavedTime = theSavedTime;
	}

	public String getDescription() {
		return myDescription;
	}

	public void setDescription(String theDescription) {
		myDescription = theDescription;
	}

	public String getName() {
		return myName;
	}

	public void setName(String theName) {
		myName = theName;
	}
}