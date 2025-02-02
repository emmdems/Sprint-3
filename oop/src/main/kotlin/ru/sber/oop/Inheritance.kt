package ru.sber.oop

open class Room(val name: String, var size: Int) {

    constructor(_name: String): this(name = _name, size = 100){}

    val monster: Monster = Goblin("Грот","пещерный","мускульный", 100, 20 )

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load(): String {
        monster.getSalutation()
        return "Nothing much to see here..."
    }

    fun Monster.getSalutation() = "Hey, I'm a Monster"
}

//TODO: create class TownSquare here...
class TownSquare(name: String = "TownSquare", size: Int = 1000): Room(name,size){

    final override fun load() = "Yeah, now I can see..."

    override val dangerLevel: Int
        get() = 2
}