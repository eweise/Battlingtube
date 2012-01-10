package com.bt.domain.reference
class Category {

  String term
  String name


  public static String ALL =  "All"
  public static String AUTOS = "Autos"
  public static String COMEDY = "Comedy"
  public static String ENTERTAINMENT = "Entertainment"
  public static String EDUCATION = "Education"
  public static String MUSIC = "Music"
  public static String FILM = "Film"
  public static String GAMING = "Gaming"
  public static String HOWTO = "Howto"
  public static String NEWS = "News"
  public static String NONPROFITS = "Nonprofits"
  public static String PEOPLE = "People"
  public static String ANIMALS = "Animals"
  public static String TECH = "Tech"
  public static String SPORTS = "Sports"
  public static String TRAVEL = "Travel"


  String toString() {
    return name;
  }

  public Category() {
  }

  public Category(def term, def name) {
    this.term = term
    this.name = name
  }


  static constraints = {
    term(nullable:false)
    name(nullable: false)
  }


}


