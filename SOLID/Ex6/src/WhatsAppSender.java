// Fixed: normalizes phone instead of throwing - doesn't tighten the precondition
public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        String phone = (n.phone != null && !n.phone.startsWith("+")) ? "+91" + n.phone : n.phone;
        System.out.println("WA -> to=" + phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
