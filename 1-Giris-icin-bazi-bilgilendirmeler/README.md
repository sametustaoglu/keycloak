# 1-Giris-icin-bazi-bilgilendirmeler

### Authentication & Authorization ?

**Authentication**, güvenliği olan bir yere girişte kimlik, yüz tanıma, parmak izi gibi kendinizi tanıtıcı uygulamalar diyebiliriz. Yani sisteme giriş yetkinizdir. Yetkisiz girişi önleme amaçlıdır.

**Authorization**, sisteme giriş yaptıktan sonra her kişi veya uygulama tüm kaynaklara erişemez. Bunun için de herkes kendisine atanan rolun yetkisi kadar hakka sahiptir. Kişi veya uygulamaların yetkilerini sınırlama amaçlıdır.

Şöyle özetleyebiliriz sisteme ilk giriş için Authentication, daha sonra içerideki kaynaklara erişim için de Authorization diyebiliriz.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled.png)

**OpenID:**

Amaç her üyelik sistemi gerektiren siteye yeni bir kullanıcı adı/şifresi yaratmak yerine OpenID’de yaratılan bir kimlikle tüm diğer sitelerde üyelik ve login gerçekleştirilebilmek.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%201.png)

OpenID bir **Federated Authentication** örneğidir.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%202.png)

**OAuth:**

OpenID projesi açık bir standart olmadığı sebebiyle geliştirildi. OAuth 2.0 2012. OpenID’de boş kalan authorization boşluğunu OAuth Protokolü’nün Token’lı yapısı sayesinde uygulamalar doldurmaya başladı.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%203.png)

Erişimin ve yetkilendirmenin sitelere direkt olarak kullanıcı adı/şifresi vermeksizin, erişimin bilgilerini barındıran bir token yapısıyla süreçleri yönetebilmek için geliştirildi. Bu süreçleri OAuth 2.0 dört adet tanımla ele alıyor.

1. Oauth2 Authorization Code Flow Örnek

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%204.png)

**Resource Owner:** Hesap sahibi

**Client Application:**  bizim Google Account’umuzdaki arkadaş bilgilerimizi almaya çalışan uygulama

**Resource Server:** Google server

**Authorization Server:** Google’ın  **XACML** standartlarında olduğunu varsaydığımız **Identity Provider**’ı.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%205.png)

### Keycloak Özellikleri

Open source IAM servisi

Apache 2.0 lisansı

2012 ilk release

Red Hat SSO olarak da geçer

**Desteklediği protokoller;** Open ID Connect, OAuth 2.0, SAML

Social Login 

User federation

Client adapter(Tomcat, [JavaScript](https://www.keycloak.org/docs/latest/securing_apps/#_javascript_adapter), [WildFly](https://www.keycloak.org/docs/latest/securing_apps/#_jboss_adapter)) entegrayonu. Library gibi. Uygulama ortamına eklenen eklentiler. Kapsam dışı olanlar için de third-party adaptörler var.

### Single Sign On(SSO)

Kabaca SSO destekleyen bir uygulamaya giriş yaptığınızda o uygulamaya yetki veren diğer uygulamalara da ayrı bir kullanıcı adı şifre girmeden ilk uygulamanın bilgileri ile girebilmektir. Örneğin gmail’e giriş yapıldığında youtube veya google drive için tekrar bilgi girişi yapmayız.  Kullanıcı bir defa authentication yaptığında tekrar tekrar yapmasına gerek kalmayacaktır. Burada dikkat etmemiz gereken husus aynı browserda açılmış olması.

Keycloak, Java ile geliştirilmiş, açık kaynaklı SSO özelliği olan kimlik doğrulama ve erişim yönetimi çözümüdür. Sadece java tabanlı değil diğer dillerinde framework’leriyle uyumludur.

Kullanıcıların merkezi yönetimi yapılabilir. 

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%208e5f4d576b28493d90441665fac2d841/Untitled%206.png)

Yukarıdaki resim, SSO çözümü örneğidir. Kullanıcı uygulamaya erişmek istediğinde uygulama tarafında keycloak server’ına yönlendirilir.