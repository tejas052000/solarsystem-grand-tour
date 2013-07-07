package net.aerospaceresearch.jplparser

import org.scalatest.{FunSpec, FunSuite}
import org.scalatest.matchers.ShouldMatchers
import JplParser._


trait ExampleFile {
  val headerContent =
    """
      |KSIZE= 2036    NCOEFF= 1018
      |
      |GROUP   1010
      |
      |JPL Planetary Ephemeris DE423/LE423
      |Start Epoch: JED=  2378480.5 1799-DEC-16-00:00:00
      |Final Epoch: JED=  2524624.5 2200-FEB-01-00:00:00
      |
      |GROUP   1030
      |
      |  2378480.50  2524624.50         32.
      |
      |GROUP   1040
      |
      |   222
      |  DENUM   LENUM   TDATEF  TDATEB  CENTER  CLIGHT  AU      EMRAT   GM1     GM2
      |  GMB     GM4     GM5     GM6     GM7     GM8     GM9     GMS     RAD1    RAD2
      |  RAD4    JDEPOC  X1      Y1      Z1      XD1     YD1     ZD1     X2      Y2
      |  Z2      XD2     YD2     ZD2     XB      YB      ZB      XDB     YDB     ZDB
      |  X4      Y4      Z4      XD4     YD4     ZD4     X5      Y5      Z5      XD5
      |  YD5     ZD5     X6      Y6      Z6      XD6     YD6     ZD6     X7      Y7
      |  Z7      XD7     YD7     ZD7     X8      Y8      Z8      XD8     YD8     ZD8
      |  X9      Y9      Z9      XD9     YD9     ZD9     XM      YM      ZM      XDM
      |  YDM     ZDM     XC      YC      ZC      XDC     YDC     ZDC     BETA    GAMMA
      |  J2SUN   GDOT    MA0001  MA0002  MA0004  MAD1    MAD2    MAD3    RE      ASUN
      |  PHI     THT     PSI     OMEGAX  OMEGAY  OMEGAZ  S31M    TAUE1   TAUE2   ROTEX
      |  ROTEY   DROTEX  COBLAT  EDOT    IFAC    KVC     PHIC    THTC    PSIC    OMGCX
      |  OMGCY   OMGCZ   AM      J2M     J3M     J4M     C22M    C31M    C32M    C33M
      |  S32M    S33M    C41M    S41M    C42M    S42M    C43M    S43M    C44M    S44M
      |  LBET    LGAM    K2M     TAUM    AE      J2E     J3E     J4E     K2E0    K2E1
      |  K2E2    TAUE0   DROTEY  GMAST1  GMAST2  GMAST3  PSIDOT  MGMIS   MA0007  MA0324
      |  MA0003  MA0006  MA0009  MA0010  MA0019  MA0020  MA0024  MA0031  MA0041  MA0052
      |  MA0139  MA0354  MA0511  MA0532  MA0654  MA0005  MA0008  MA0013  MA0014  MA0015
      |  MA0016  MA0018  MA0022  MA0023  MA0027  MA0029  MA0045  MA0051  MA0065  MA0078
      |  MA0097  MA0105  MA0111  MA0344  MA0372  MA0405  MA0409  MA0451  MA0704  MA0747
      |  MA0011  MA0021  MA0025  MA0028  MA0030  MA0042  MA0060  MA0063  MA0069  MA0094
      |  MA0098  MA0135  MA0145  MA0187  MA0192  MA0194  MA0216  MA0230  MA0337  MA0419
      |  MA0488  MA0554
      |
      |GROUP   1041
      |
      |   222
      |  0.423000000000000000D+03  0.423000000000000000D+03  0.201002051148400000D+14
      |  0.201002051137180000D+14  0.110000000000000000D+02  0.299792458000000000D+06
      |  0.149597870699626200D+09  0.813005694159985700D+02  0.491249717333700100D-10
      |  0.724345233269844100D-09  0.899701140826804900D-09  0.954954869562239000D-10
      |  0.282534584085505000D-06  0.845970607330847800D-07  0.129202482579265000D-07
      |  0.152435910924974000D-07  0.217844105199052000D-11  0.295912208285591100D-03
      |  0.243974914235321100D+04  0.605891954429445800D+04  0.339751500000000000D+04
      |  0.244040050000000000D+07  0.357260207968922000D+00 -0.915490465806868600D-01
      | -0.859810316497157600D-01  0.336784565359036600D-02  0.248893426193410000D-01
      |  0.129440719308013200D-01  0.608249432889032700D+00 -0.349132444885303400D+00
      | -0.195544343079136900D+00  0.109524201411561900D-01  0.156125066590703600D-01
      |  0.632887661112843700D-02  0.116014907965908800D+00 -0.926605551320249000D+00
      | -0.401806283286235100D+00  0.168116200547048900D-01  0.174313163641738300D-02
      |  0.755973832070689100D-03 -0.114688581313187000D+00 -0.132836652742114800D+01
      | -0.606155197567064200D+00  0.144820048080607600D-01  0.237285482729449800D-03
      | -0.283749789724721500D-03 -0.538420927273348900D+01 -0.831248400455448600D+00
      | -0.225095123251954600D+00  0.109236443591119200D-02 -0.652329413823768300D-02
      | -0.282301205665306300D-02  0.788988816882537700D+01  0.459571098831611500D+01
      |  0.155842977730162000D+01 -0.321720477088762600D-02  0.433063271514981900D-02
      |  0.192641720980771400D-02 -0.182699027041829100D+02 -0.116272404728390300D+01
      | -0.250369921311548900D+00  0.221541848326055400D-03 -0.376765323311565500D-02
      | -0.165324329742406400D-02 -0.160595405811532000D+02 -0.239429537947765100D+02
      | -0.940041829916465200D+01  0.264312186915816600D-02 -0.150349118209930500D-02
      | -0.681271456604641100D-03 -0.304878261205091900D+02 -0.873186296008797700D+00
      |  0.891130397300355000D+01  0.322560744260970400D-03 -0.314875311563799000D-02
      | -0.108017904134281400D-02 -0.808177351031068800D-03 -0.199462999003197400D-02
      | -0.108726267806487500D-02  0.601084816172660400D-03 -0.167445469096584100D-03
      | -0.855621411371812700D-04 -0.450250961300037600D-02 -0.767077603471155600D-03
      | -0.266058255642232200D-03  0.351749636449403300D-06 -0.517762612647710200D-05
      | -0.222910215263254800D-05  0.100000000000000000D+01  0.100000000000000000D+01
      |  0.200233667110366200D-06  0.000000000000000000D+00  0.138320560611157100D-12
      |  0.293217094250374800D-13  0.383747110390088500D-13  0.114626072964000600D+01
      |  0.218413841207515900D+01  0.355070175358934300D+01  0.637813630000000000D+04
      |  0.696000000000000000D+06  0.512813205871436300D-02  0.382393200523006700D+00
      |  0.129416805605708200D+01  0.458292267886046700D-04 -0.218563408167638200D-05
      |  0.229944861000397100D+00  0.590080000000000000D-05  0.111424509688019100D-01
      |  0.657429224597163500D-02  0.578107994676251600D-02 -0.169156013665773400D-01
      |  0.256623345340439400D-03  0.379771293691438700D-03  0.000000000000000000D+00
      |  0.700000000000000000D-03  0.149376024930566100D-07 -0.425951830000000000D-02
      |  0.408844300000000000D+00 -0.171450900000000000D+01 -0.677701622915927600D-02
      | -0.114975146821343500D-02  0.229470176943708000D+00  0.173800000000000000D+04
      |  0.203273257637072400D-03  0.840470152594100000D-05 -0.964228599999999900D-05
      |  0.223897670965241300D-04  0.284524350000000000D-04  0.484638724000903000D-05
      |  0.167404753003914200D-05  0.168419847414760000D-05 -0.248552600000000000D-06
      | -0.569268700000000000D-05  0.157439300000000000D-05 -0.158620000000000000D-05
      | -0.151731200000000000D-05 -0.812041000000000000D-07 -0.802790700000000100D-06
      | -0.127394100000000000D-06  0.831475000000000000D-07  0.631002202536463000D-03
      |  0.227730531419914200D-03  0.216336838636074100D-01  0.107858681985658800D+00
      |  0.637813630000000000D+04  0.108262530500000000D-02 -0.253247400000000000D-05
      |  0.161997400000000000D-05  0.335000000000000000D+00  0.320000000000000000D+00
      |  0.320000000000000000D+00  0.640000000000000000D-01 -0.114817217603685000D-02
      |  0.398810453309753200D-13  0.721165574560146800D-14  0.264905964471570200D-14
      |  0.000000000000000000D+00  0.100000000000000000D+01  0.196678811719346500D-14
      |  0.155311188298059400D-14  0.376897122601597500D-14  0.177501654709354400D-14
      |  0.105258570090524400D-14  0.130843342065509800D-13  0.105041722950070200D-14
      |  0.648480992280597900D-15  0.897526757071915400D-15  0.254045354831839900D-14
      |  0.777837262317542200D-15  0.287732612990711800D-14  0.419157623347932800D-15
      |  0.728422406074963600D-15  0.271810578806548500D-14  0.156916697660985700D-14
      |  0.199961567242721600D-15  0.354715862895056400D-15  0.440396873437455400D-15
      |  0.919323722227646200D-15  0.694395117769957300D-15  0.380280349099850900D-14
      |  0.449486299388165200D-14  0.594426051415870700D-15  0.109435890365062900D-14
      |  0.482988292192649000D-15  0.187781048066757700D-15  0.228690112008867400D-14
      |  0.885287061421740700D-15  0.320108061167712300D-15  0.154772751838264200D-14
      |  0.189074621274620900D-15  0.198195016125008700D-15  0.196597317770212000D-15
      |  0.259089979105200000D-15  0.253156132749382100D-15  0.791909732954347900D-15
      |  0.205848314021677500D-15  0.482706169069880700D-15  0.135959136216236800D-14
      |  0.567722492240966900D-14  0.435957510093908600D-15  0.793952483511378600D-15
      |  0.310486497619801300D-15  0.894617916124605600D-16  0.367825010444715300D-15
      |  0.211049438451158200D-15  0.204215392645001200D-15  0.466750236112845300D-16
      |  0.228321394561439600D-15  0.924035340232315500D-15  0.924019484634906100D-15
      |  0.122837967550319000D-15  0.174360680221991100D-15  0.336720129250530600D-15
      |  0.233516838837633200D-15  0.237743051467384300D-15  0.405560727824356200D-15
      |  0.667373533549140700D-15  0.280234242242660700D-15  0.727196170127968500D-16
      |  0.227354748220404900D-15  0.364596802695516200D-15  0.986552943269781400D-16
      |
      |GROUP   1050
      |
      |     3   171   231   309   342   366   387   405   423   441   753   819   899
      |    14    10    13    11     8     7     6     6     6    13    11    10    10
      |     4     2     2     1     1     1     1     1     1     8     2     4     4
      |
      |GROUP   1070
    """.stripMargin
}

trait ConstantGroupsTestData {

  val rawNames =
    """1040
      |
      |   222
      |  DENUM   LENUM   TDATEF
      |  TDATEB  CENTER  CLIGHT
    """.stripMargin
  val rawValues =
    """1041
      |
      |   222
      |  0.423000000000000000D+03  0.423000000000000000D+03  0.201002051148400000D+14
      |  0.201002051137180000D+14  0.110000000000000000D+02  0.299792458000000000D+06
      |
    """.stripMargin

  val rawNamesWithLeadingWhitespace =
    """                    1040
      |
      |   222
      |  DENUM   LENUM   TDATEF
      |  TDATEB  CENTER  CLIGHT
    """.stripMargin
  val rawValuesWithLeadingWhitespace =
    """
      |1041
      |
      |   222
      |  0.423000000000000000D+03  0.423000000000000000D+03  0.201002051148400000D+14
      |  0.201002051137180000D+14  0.110000000000000000D+02  0.299792458000000000D+06
      |
    """.stripMargin

  val referenceMap = Map(
    "DENUM" -> BigDecimal("0.423000000000000000E+03"),
    "LENUM" -> BigDecimal("0.423000000000000000E+03"),
    "TDATEF" -> BigDecimal("0.201002051148400000E+14"),
    "TDATEB" -> BigDecimal("0.201002051137180000E+14"),
    "CENTER" -> BigDecimal("0.110000000000000000E+02"),
    "CLIGHT" -> BigDecimal("0.299792458000000000E+06")
  )
}

trait TripletTestData {
  val tripletGroup =
    """
      |   1050
      |
      |     3   171   231   309   342   366   387   405   423   441   753   819   899
      |    14    10    13    11     8     7     6     6     6    13    11    10    10
      |     4     2     2     1     1     1     1     1     1     8     2     4     4
    """.stripMargin
}

/**
 * Created by elmar on 06.07.13.
 */
class ParserSec extends FunSpec with ShouldMatchers {

  describe("The JPL Parser") {

    it("should convert JPL style decimals to BigDecimal") {
      expectResult(BigDecimal("0.216336838636074100E-01")) {
        JplParser.parseDecimal("0.216336838636074100D-01")
      }
    }

    it("should read the constant groups into a Map") {
      new ConstantGroupsTestData {

        expectResult(referenceMap) {
          JplParser.parseConstantGroups(rawNamesWithLeadingWhitespace, rawValuesWithLeadingWhitespace)
          JplParser.parseConstantGroups(rawNames, rawValues)
        }
      }
    }

    it("should read the first triplet") {
      new TripletTestData {
        expectResult((3, 14, 4)) {
          JplParser.parseTriplets(tripletGroup).head
        }
      }
    }

    it("should read the last triplet") {
      new TripletTestData {
        expectResult((899, 10, 4)) {
          JplParser.parseTriplets(tripletGroup).last
        }
      }
    }

    it("should calculate the number of records based on the parsed triplet") {
      new TripletTestData {
        expectResult(1016) {
          numberOfRecordsPerInterval(parseTriplets(tripletGroup))
        }
      }
    }

    it("should normalize strings") {
      val multiline =
        """
          |    a
          |    b    c d
          |e f
          |g   h  i
          |
          |j
          |
          |
          |
          |k
        """.stripMargin
      expectResult("a b c d e f g h i j k") {
        normalize(multiline)
      }
    }
  }
}