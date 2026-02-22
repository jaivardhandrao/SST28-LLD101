// Fixed: includes subject like other senders to honor the full Notification contract
public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        System.out.println("SMS -> to=" + n.phone + " subject=" + n.subject + " body=" + n.body);
        audit.add("sms sent");
    }
}
