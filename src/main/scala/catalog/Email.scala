package catalog

import java.util.Properties

import catalog.CatalogParser.logger
import javax.mail._
import javax.mail.internet.{InternetAddress, MimeMessage}

import scala.util.Try

object Email {

  def sendEmail(subject: String, text: String, mainRecipients: List[String]): Unit = {
    val prop = new Properties()
    prop.put("mail.smtp.host", "smtp.gmail.com")
    prop.put("mail.smtp.port", "465")
    prop.put("mail.smtp.auth", "true")
    prop.put("mail.smtp.socketFactory.port", "465")
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")

    val user = sys.env("USER")
    val password = sys.env("PASSWORD")
    val envRecipients = Try(sys.env("RECIPIENTS").split(",").toList).getOrElse(List.empty)
    if (envRecipients.isEmpty && mainRecipients.isEmpty) throw new VerifyError("No email found")
    val recipients: List[String] = envRecipients ::: mainRecipients

    val session: Session = Session.getInstance(prop, new Authenticator() {
      override protected def getPasswordAuthentication = new PasswordAuthentication(user, password)
    })

    val message = new MimeMessage(session)
    message.setFrom(new InternetAddress(user))
    recipients.foreach{ recipient =>
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient))
    }
    message.setSubject(subject)
    message.setText(text)
    Transport.send(message)
    logger.info("Email sent to {}", recipients.mkString(", "))
  }
}