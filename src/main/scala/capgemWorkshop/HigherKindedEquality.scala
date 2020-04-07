//package capgemWorkshop
//
//import scala.language.higherKinds
//
//trait HigherKindedEquality[F[_]] {
//  def eq[A](e1: F[A], e2: F[A]): Boolean
//}
//
//object HigherKindedEquality {
//  def apply[A](implicit higherKindedEquality: HigherKindedEquality[A]): HigherKindedEquality[A] = higherKindedEquality
//
////  implicit def listEquality = new HigherKindedEquality[List] {
////    override def eq[A](e1: List[A], e2: List[A])
////  }
//}
