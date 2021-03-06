package Model_old

import Model_old.Agario_objects._

import scala.collection.mutable.ListBuffer
import scala.math.{Pi, sqrt}

object Games {
//olddd
  def Elimination(User: Players, Online_Player: Online): ListBuffer[Players] = {
    for (online_player <- Online_Player.online_Players) {
      val distance = sqrt(math.pow(User.Player.x - online_player.Player.x, 2) - math.pow(User.Player.y - online_player.Player.y, 2))
      if (User.Player.r > online_player.Player.r) {
        if (distance < User.Player.r + online_player.Player.r) {
          //          d
          val sum = Pi * math.pow(User.Player.r, 2) + Pi * math.pow(online_player.Player.r, 2)
          User.Player.r = sqrt(sum / Pi)
          online_player.status = false
          //remove the eaten online player
          Online_Player.online_Players -= online_player
        }
      }
      else {
        Online_Player.online_Players -= User
      }
    }
    Online_Player.online_Players
  }

  def Boundary_hit(users: Players, bounds: Boundary): Boolean = {
    var hit = false
    val distance = sqrt(math.pow(users.Player.x - bounds.start.x, 2) - math.pow(users.Player.y - bounds.start.y, 2))
    if (distance < users.Player.r) {
      hit = true
      //true means it hit the boundary
    }
    hit
  }

  def Winner(Online_player: Online): String = {
    //we will check when the list hits one in a if statement and return the user
    val Player = new Positions(0, 0, 0)
    var winner = new Players(Player, true, "name")
    if (Online_player.online_Players.length == 1) {
      for (winners <- Online_player.online_Players) {
        winner = winners
      }
    }
    winner.name
  }

  def hit_Food(User: Players, world: World): Unit = {
    for (food <- world.Foods) {
      val distance = sqrt(math.pow(User.Player.x - food.x, 2) - math.pow(User.Player.y - food.y, 2))
      if (distance < User.Player.r + food.r) {
        val sum = Pi * math.pow(User.Player.r, 2) + Pi * math.pow(food.r, 2)
        User.Player.r = sqrt(sum / Pi)
        world.Foods -= food
      }
    }

  }
}
