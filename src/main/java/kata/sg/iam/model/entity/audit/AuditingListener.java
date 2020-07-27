package kata.sg.iam.model.entity.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditingListener {

  @PrePersist
  public void setCreatedAt(Auditable auditable) {
    Audit audit = auditable.getAudit();

    if (audit == null) {
      audit = new Audit();
      auditable.setAudit(audit);
    }

    Instant now = Instant.now();

    audit.setCreatedAt(now);
    audit.setLastUpdatedAt(now);
  }

  @PreUpdate
  public void setUpdatedOn(Auditable auditable) {
    Audit audit = auditable.getAudit();
    if(audit != null) {
      Instant now = Instant.now();
      audit.setLastUpdatedAt(now);
    }
  }

}
