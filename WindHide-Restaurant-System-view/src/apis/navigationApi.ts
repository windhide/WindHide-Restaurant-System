import {routes} from '@/router/index'

class Navigation {
    children: any
    path: string
    constructor(path: string) {
        this.path = path;
    }
}

class ChildrenNavigation {
    childrenPath: string
    childrenName: string
    icon: string
    children: any
    constructor(childrenPath: string, childrenName: string, icon: string) {
        this.childrenName = childrenName
        this.childrenPath = childrenPath
        this.icon = icon
    }
}

// 判断是否拥有子节点
export function hasChildren(object: any) {
    for (let key in object) {
        if (typeof (object[key]) == "object" && key != "prop") {
            if (object[key].length >= 1)
                return true
        }
    }
    return false
}

/**
 * @Anothor 获取导航节点
 * @param path 
 * @returns 
 */
export function getNavigation(path: String) {
    const navigation = new Navigation("/" + path);
    let cacheChildrenList: any = []
    routes.filter(_ => { return _.path == navigation.path }).forEach((data: any) => {

        data.children.forEach((children: any) => {
            let inCacheChildrenList: any = []
            let cache = new ChildrenNavigation("/"+children.path, children.name, children.props.icon)
            if (hasChildren(children)) {
                children.children.forEach((inChildren: any) => {
                    const inCache = new ChildrenNavigation("/"+inChildren.path, inChildren.name, inChildren.props.icon)
                    inCacheChildrenList.push(inCache)
                });
            }
            cache.children = inCacheChildrenList
            cacheChildrenList.push(cache)
        });
    })
    navigation.children = cacheChildrenList
    return navigation;
}