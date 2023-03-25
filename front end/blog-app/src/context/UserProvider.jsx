import {React, useEffect, useState} from 'react'
import UserContext from './UserContext'
import { getCurrentUserDetail, isloggedin } from '../Auth'

function UserProvider({children}) {
  
    const [user, setUser] = useState(({
        data: {},
        login: false
    }))
    useEffect(() => {
        setUser({
            data:getCurrentUserDetail(),
            login: isloggedin()
        })
    }, [])

    return (
    <UserContext.Provider value={{user, setUser}}>
        {children}
    </UserContext.Provider>
  )
}

export default UserProvider