/*
 * Copyright 2013-2016 Tsukasa Kitachi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package configs.syntax

import com.typesafe.config.Config
import configs.{Attempt, Configs}

object attempt {

  implicit class ConfigOps(private val self: Config) extends AnyVal {

    def extract[A](implicit A: Configs[A]): Attempt[A] =
      A.extract(self)

    def get[A](path: String)(implicit A: Configs[A]): Attempt[A] =
      A.get(self, path)

    def getOpt[A: Configs](path: String): Attempt[Option[A]] =
      get[Option[A]](path)

    def getOrElse[A: Configs](path: String, default: => A): Attempt[A] =
      getOpt[A](path).map(_.getOrElse(default))

  }

}