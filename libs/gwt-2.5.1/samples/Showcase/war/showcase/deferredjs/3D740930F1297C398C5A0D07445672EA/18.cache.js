function V6(){}
function qrc(a,b){DB(a.a,b)}
function cVb(a,b){this.b=a;this.a=b}
function fVb(a,b){this.b=a;this.a=b}
function s7(a){return jwb(d7,a)}
function U6(){U6=_Qc;T6=new V6}
function Drc(){yrc();Crc.call(this,qr($doc,'password'),'gwt-PasswordTextBox')}
function WUb(a,b){sjc(b,'\u0645\u062E\u062A\u0627\u0631\u0629: '+a.$g()+gVc+a._g())}
function oAc(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;return c.text.length}catch(a){return 0}}
function nAc(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;return -c.move(R$c,-65535)}catch(a){return 0}}
function VUb(a,b){var c,d;c=new Unc;c.e[$Xc]=4;Rnc(c,a);if(b){d=new wjc('\u0645\u062E\u062A\u0627\u0631\u0629: 0, 0');jj(a,new cVb(a,d),(Wx(),Wx(),Vx));jj(a,new fVb(a,d),(kx(),kx(),jx));Rnc(c,d)}return c}
function qAc(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;var d=c.text.length;var e=0;var f=c.duplicate();f.moveEnd(R$c,-1);var g=f.text.length;while(g==d&&f.parentElement()==b&&c.compareEndPoints('StartToEnd',f)<=0){e+=2;f.moveEnd(R$c,-1);g=f.text.length}return d+e}catch(a){return 0}}
function pAc(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;var d=c.duplicate();d.moveToElementText(b);d.setEndPoint('EndToStart',c);var e=d.text.length;var f=0;var g=d.duplicate();g.moveEnd(R$c,-1);var i=g.text.length;while(i==e&&g.parentElement()==b){f+=2;g.moveEnd(R$c,-1);i=g.text.length}return e+f}catch(a){return 0}}
var R$c='character',Q$c='\u0642\u0631\u0627\u0621\u0629 \u0641\u0642\u0637';svb(432,433,{},V6);_.ye=function W6(a){return s7((m7(),a))?(HF(),GF):(HF(),FF)};var T6;svb(818,1,VRc);_.lc=function aVb(){var a,b,c,d,e,f;ayb(this.a,(a=new $yc,a.e[$Xc]=5,b=new Brc,Cyc(b.cb,BTc,'cwBasicText-textbox'),qrc(b,(U6(),U6(),T6)),c=new Brc,Cyc(c.cb,BTc,'cwBasicText-textbox-disabled'),c.cb[PZc]=Q$c,CB(c.a),c.cb[GZc]=true,Xyc(a,new Bjc('<b>\u0645\u0631\u0628\u0639 \u0646\u0635 \u0639\u0627\u062F\u064A:<\/b>')),Xyc(a,VUb(b,true)),Xyc(a,VUb(c,false)),d=new Drc,Cyc(d.cb,BTc,'cwBasicText-password'),e=new Drc,Cyc(e.cb,BTc,'cwBasicText-password-disabled'),e.cb[PZc]=Q$c,CB(e.a),e.cb[GZc]=true,Xyc(a,new Bjc('<br><br><b>\u0645\u0631\u0628\u0639 \u0646\u0635 \u0643\u0644\u0645\u0629 \u0627\u0644\u0633\u0631:<\/b>')),Xyc(a,VUb(d,true)),Xyc(a,VUb(e,false)),f=new Nwc,Cyc(f.cb,BTc,'cwBasicText-textarea'),f.cb.rows=5,Xyc(a,new Bjc('<br><br><b>\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0646\u0635:<\/b>')),Xyc(a,VUb(f,true)),a))};svb(819,1,DRc,cVb);_.Fc=function dVb(a){WUb(this.b,this.a)};_.a=null;_.b=null;svb(820,1,SRc,fVb);_.Dc=function gVb(a){WUb(this.b,this.a)};_.a=null;_.b=null;svb(1161,1044,iRc);_.$g=function trc(){return nAc(this.cb)};_._g=function urc(){return oAc(this.cb)};svb(1158,1159,iRc,Drc);svb(1214,1160,iRc);_.$g=function Owc(){return pAc(this.cb)};_._g=function Pwc(){return qAc(this.cb)};var Lkb=kGc(VYc,'CwBasicText$2',819),Mkb=kGc(VYc,'CwBasicText$3',820),Epb=kGc(PYc,'PasswordTextBox',1158),ffb=kGc(pZc,'AnyRtlDirectionEstimator',432);ISc(vn)(18);