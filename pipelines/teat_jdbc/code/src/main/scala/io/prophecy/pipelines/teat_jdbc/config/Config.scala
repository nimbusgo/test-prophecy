package io.prophecy.pipelines.teat_jdbc.config

import pureconfig._
import pureconfig.generic.ProductHint
import io.prophecy.libs._

case class Config(var username: String = "asdf", var pass: String = "asdf")
    extends ConfigBase
