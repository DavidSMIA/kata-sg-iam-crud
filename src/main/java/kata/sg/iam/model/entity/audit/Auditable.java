package kata.sg.iam.model.entity.audit;

public interface Auditable {

  Audit getAudit();

  void setAudit(Audit audit);

}
