function TBb(a){this.b=a}
function WBb(a){this.b=a}
function xyc(a){this.b=a}
function Dyc(a){this.d=a;this.c=a.b.c.b}
function uyc(a){vyc.call(this,a,null,null)}
function tyc(a){a.b.c=a.c;a.c.b=a.b;a.b=a.c=null}
function syc(a){var b;b=a.d.c.c;a.c=b;a.b=a.d.c;b.b=a.d.c.c=a}
function byc(a,b){if(a.b){tyc(b);syc(b)}}
function $xc(a,b){return a.d.je(b)}
function vyc(a,b,c){this.d=a;oyc.call(this,b,c);this.b=this.c=null}
function Cyc(a){if(a.c==a.d.b.c){throw new Kyc}a.b=a.c;a.c=a.c.b;return a.b}
function _xc(a,b){var c;c=AU(a.d.me(b),157);if(c){byc(a,c);return c.f}return null}
function cyc(){gtc(this);this.c=new uyc(this);this.d=new Hxc;this.c.c=this.c;this.c.b=this.c}
function zib(a){var b,c;b=AU(a.b.me(DKc),149);if(b==null){c=qU(Kdb,kAc,1,['Bonjour le monde',EKc,FKc]);a.b.oe(DKc,c);return c}else{return b}}
function ayc(a,b,c){var d,e,f;e=AU(a.d.me(b),157);if(!e){d=new vyc(a,b,c);a.d.oe(b,d);syc(d);return null}else{f=e.f;nyc(e,c);byc(a,e);return f}}
function GBb(b){var c,d,e,f;e=Q7b(b.e,b.e.db.selectedIndex);c=AU(_xc(b.g,e),121);try{f=Apc(ur(b.f.db,TIc));d=Apc(ur(b.d.db,TIc));rZb(b.b,c,d,f)}catch(a){a=Sdb(a);if(CU(a,145)){return}else throw a}}
function EBb(a){var b,c,d,e;d=new h5b;a.f=new Gac;cj(a.f,GKc);wac(a.f,'100');a.d=new Gac;cj(a.d,GKc);wac(a.d,'60');a.e=new W7b;$4b(d,0,0,'<b>Points \xE0 circuler:<\/b>');b5b(d,0,1,a.e);$4b(d,1,0,'<b>Bord du dessus:<\/b>');b5b(d,1,1,a.f);$4b(d,2,0,'<b>Bord gauche:<\/b>');b5b(d,2,1,a.d);for(c=Luc(cI(a.g));c.b.xe();){b=AU(Ruc(c),1);R7b(a.e,b)}vj(a.e,new TBb(a),(kx(),kx(),jx));e=new WBb(a);vj(a.f,e,(ey(),ey(),dy));vj(a.d,e,dy);return d}
function FBb(a){var b,c,d,e,f,g,i,j;a.g=new cyc;a.b=new tZb;$i(a.b,HKc,HKc);Ui(a.b,IKc);j=zib(a.c);i=new G2b('Hello World');mZb(a.b,i,10,20);ayc(a.g,j[0],i);c=new n$b('Cliquez-moi!');mZb(a.b,c,80,45);ayc(a.g,j[1],c);d=new J5b(2,3);d.p[wFc]=PGc;for(e=0;e<3;++e){$4b(d,0,e,e+FCc);b5b(d,1,e,new bWb((wjb(),ljb)))}mZb(a.b,d,60,100);ayc(a.g,j[2],d);b=new R1b;Wj(b,a.b);g=new R1b;Wj(g,EBb(a));f=new Z6b;f.f[cHc]=10;W6b(f,g);W6b(f,b);return f}
var GKc='3em',DKc='cwAbsolutePanelWidgetNames';Meb(736,1,ZAc);_.qc=function RBb(){phb(this.c,FBb(this.b))};Meb(737,1,XAc,TBb);_.Gc=function UBb(a){HBb(this.b)};_.b=null;Meb(738,1,HAc,WBb);_.Jc=function XBb(a){GBb(this.b)};_.b=null;Meb(1322,1320,KBc,cyc);_.Dh=function dyc(){this.d.Dh();this.c.c=this.c;this.c.b=this.c};_.je=function eyc(a){return this.d.je(a)};_.ke=function fyc(a){var b;b=this.c.b;while(b!=this.c){if(bAc(b.f,a)){return true}b=b.b}return false};_.le=function gyc(){return new xyc(this)};_.me=function hyc(a){return _xc(this,a)};_.oe=function iyc(a,b){return ayc(this,a,b)};_.pe=function jyc(a){var b;b=AU(this.d.pe(a),157);if(b){tyc(b);return b.f}return null};_.qe=function kyc(){return this.d.qe()};_.b=false;Meb(1323,1324,{157:1,160:1},uyc,vyc);_.b=null;_.c=null;_.d=null;Meb(1325,364,MAc,xyc);_.te=function yyc(a){var b,c,d;if(!CU(a,160)){return false}b=AU(a,160);c=b.Ae();if($xc(this.b,c)){d=_xc(this.b,c);return bAc(b.Pc(),d)}return false};_.cc=function zyc(){return new Dyc(this)};_.qe=function Ayc(){return this.b.d.qe()};_.b=null;Meb(1326,1,{},Dyc);_.xe=function Eyc(){return this.c!=this.d.b.c};_.ye=function Fyc(){return Cyc(this)};_.ze=function Gyc(){if(!this.b){throw new Gpc('No current entry')}tyc(this.b);this.d.b.d.pe(this.b.e);this.b=null};_.b=null;_.c=null;_.d=null;var x3=npc(VHc,'CwAbsolutePanel$3',737),y3=npc(VHc,'CwAbsolutePanel$4',738),Mcb=npc(gIc,'LinkedHashMap',1322),Jcb=npc(gIc,'LinkedHashMap$ChainEntry',1323),Lcb=npc(gIc,'LinkedHashMap$EntrySet',1325),Kcb=npc(gIc,'LinkedHashMap$EntrySet$EntryIterator',1326);MBc(In)(21);