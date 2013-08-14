/*
 * Copyright (c) 2013 Elmar Athmer
 *
 * This file is part of SolarSystemGrandTour.
 *
 * SolarSystemGrandTour is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SolarSystemGrandTour is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SolarSystemGrandTour.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.aerospaceresearch.model

import breeze.linalg.DenseVector
import net.aerospaceresearch.jplparser.Types
import scala.collection.parallel.immutable.ParSeq

/**
 * This class presents a solar system to a given point in time
 * User: Elmar Athmer
 * Date: 17.06.13
 * Time: 23:34
 */
class SolarSystem(val bodies: List[Body], val centerMass: Body, val time: Types.JulianTime) {
  val allBodies = centerMass :: bodies

  def nextStep(time: Int): SolarSystem = {
    this
  }

  def forces: ParSeq[List[DenseVector[Double]]] =
    bodies.par.map {
      body => body.forcesExperienced(allBodies.filter(_ != body))
    }

  def accelerations: ParSeq[DenseVector[Double]] =
    bodies.par.map {
      body => body.acceleration(allBodies.filter(_ != body))
    }

  def velocities: ParSeq[DenseVector[Double]] =
    bodies.par.map {
      body => body.v1(allBodies.filter(_ != body))
    }

  def positions: ParSeq[DenseVector[Double]] =
    bodies.par.map {
      body => body.r1(allBodies.filter(_ != body))
    }

}
