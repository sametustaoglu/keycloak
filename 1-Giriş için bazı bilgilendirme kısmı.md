### Authentication & Authorization ?

**Authentication**, güvenliği olan bir yere girişte kimlik, yüz tanıma, parmak izi gibi kendinizi tanıtıcı uygulamalar diyebiliriz. Yani sisteme giriş yetkinizdir diyebiliriz. Yetkisiz girişi önleme amaçlıdır.

**Authorization**, sisteme giriş yaptıktan sonra her kişi veya uygulama tüm kaynaklara erişemez. Bunun için de herkes kendisine atanan rolun yetkisi kadar hakka sahiptir. Kişi veya uygulamaların yetkilerini sınırlama amaçlıdır.

Şöyle özetleyebiliriz sisteme ilk giriş için Authentication, daha sonra içerideki kaynaklara erişim için de Authorization diyebiliriz.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c1bea567-16cf-4c41-87b9-496404c33856/Untitled.png)

### Single Sign On(SSO)

Kabaca SSO destekleyen bir uygulamaya giriş yaptığınızda o uygulamaya yetki veren diğer uygulamalara da ayrı bir kullanıcı adı şifre girmeden ilk uygulamanın bilgileri ile girebilmektir. Örneğin gmail’e giriş yapıldığında youtube veya google drive için tekrar bilgi girişi yapmayız.  Kullanıcı bir defa authentication yaptığında tekrar tekrar yapmasına gerek kalmayacaktır. Burada dikkat etmemiz gereken husus aynı browserda açılmış olması.

### Keycloak

Keycloak, Java ile geliştirilmiş, açık kaynaklı SSO özelliği olan kimlik doğrulama ve erişim yönetimi çözümüdür. Sadece java tabanlı değil diğer dillerinde framework’leriyle uyumludur.

Kullanıcıların merkezi yönetimi yapılabilir. 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c4c4885a-f314-4d68-b11d-033ff127fbf0/Untitled.png)

Yukarıdaki resim, SSO çözümü örneğidir. Kullanıcı uygulamaya erişmek istediğinde uygulama tarafında keycloak server’ına yönlendirilir.