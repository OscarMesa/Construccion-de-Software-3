function ykb(a){this.b=a}
function vkb(a,b){this.b=a;this.c=b}
function rkb(a){var b,c,d;b=Bdc(ur(a.b.db,Tvc));c=Bdc(ur(a.c.db,Tvc));d=Bdc(ur(a.d.db,Tvc));xRb(a.e,"User '"+b+"' has security clearance '"+c+"' and cannot access '"+d+Orc)}
function qkb(a){var b,c,d,e,f,g;d=new hUb;b=ZH(d.k,98);d.p[$tc]=5;g=Y3(PR);e=new UMb(g);vj(e,new vkb(a,g),(ux(),ux(),tx));f=new ZVb;f.f[$tc]=3;WVb(f,new GRb(Pyc));WVb(f,e);bUb(d,0,0,f);qUb(b,0)[Svc]=2;$Tb(d,1,0,Qyc);$Tb(d,1,1,"User '{0}' has security clearance '{1}' and cannot access '{2}'");a.b=new GZb;wZb(a.b,'amelie');$Tb(d,2,0,Ryc);bUb(d,2,1,a.b);a.c=new GZb;wZb(a.c,'guest');$Tb(d,3,0,Syc);bUb(d,3,1,a.c);a.d=new GZb;wZb(a.d,'/secure/blueprints.xml');$Tb(d,4,0,Tyc);bUb(d,4,1,a.d);a.e=new ERb;$Tb(d,5,0,Zyc);bUb(d,5,1,a.e);vUb(b,5,0,(tVb(),sVb));c=new ykb(a);vj(a.b,c,(ey(),ey(),dy));vj(a.c,c,dy);vj(a.d,c,dy);rkb(a);return d}
M1(641,1,Wnc,vkb);_.Hc=function wkb(a){R3(this.b,this.c+Xyc)};_.b=null;_.c=null;M1(642,1,Hnc,ykb);_.Jc=function zkb(a){rkb(this.b)};_.b=null;M1(643,1,Znc);_.qc=function Dkb(){p4(this.c,qkb(this.b))};var PR=pcc(Xuc,'ErrorMessages'),CR=ncc(Xuc,'CwMessagesExample$1',641),DR=ncc(Xuc,'CwMessagesExample$2',642);Moc(In)(29);