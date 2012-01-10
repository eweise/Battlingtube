class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }
        "500"(view: '/error')
        "/"(controller:"main", action:"list")
    }
}