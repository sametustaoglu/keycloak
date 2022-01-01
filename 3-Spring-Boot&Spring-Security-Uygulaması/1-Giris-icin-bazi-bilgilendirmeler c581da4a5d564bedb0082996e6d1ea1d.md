# 1-Giris-icin-bazi-bilgilendirmeler

### Authentication & Authorization ?

**Authentication**, güvenliği olan bir yere girişte kimlik, yüz tanıma, parmak izi gibi kendinizi tanıtıcı uygulamalar diyebiliriz. Yani sisteme giriş yetkinizdir diyebiliriz. Yetkisiz girişi önleme amaçlıdır.

**Authorization**, sisteme giriş yaptıktan sonra her kişi veya uygulama tüm kaynaklara erişemez. Bunun için de herkes kendisine atanan rolun yetkisi kadar hakka sahiptir. Kişi veya uygulamaların yetkilerini sınırlama amaçlıdır.

Şöyle özetleyebiliriz sisteme ilk giriş için Authentication, daha sonra içerideki kaynaklara erişim için de Authorization diyebiliriz.

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%20c581da4a5d564bedb0082996e6d1ea1d/Untitled.png)

### Single Sign On(SSO)

Kabaca SSO destekleyen bir uygulamaya giriş yaptığınızda o uygulamaya yetki veren diğer uygulamalara da ayrı bir kullanıcı adı şifre girmeden ilk uygulamanın bilgileri ile girebilmektir. Örneğin gmail’e giriş yapıldığında youtube veya google drive için tekrar bilgi girişi yapmayız.  Kullanıcı bir defa authentication yaptığında tekrar tekrar yapmasına gerek kalmayacaktır. Burada dikkat etmemiz gereken husus aynı browserda açılmış olması.

### Keycloak

Keycloak, Java ile geliştirilmiş, açık kaynaklı SSO özelliği olan kimlik doğrulama ve erişim yönetimi çözümüdür. Sadece java tabanlı değil diğer dillerinde framework’leriyle uyumludur.

Kullanıcıların merkezi yönetimi yapılabilir. 

![Untitled](1-Giris-icin-bazi-bilgilendirmeler%20c581da4a5d564bedb0082996e6d1ea1d/Untitled%201.png)

Yukarıdaki resim, SSO çözümü örneğidir. Kullanıcı uygulamaya erişmek istediğinde uygulama tarafında keycloak server’ına yönlendirilir.