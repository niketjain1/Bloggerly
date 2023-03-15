import { NavLink as ReactLink, useNavigate } from 'react-router-dom';

import React, { useEffect, useState } from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from 'reactstrap';
import { doLogout, getCurrentUserDetail, isloggedin } from '../Auth';

const CustomNavbar = () => {

  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  const navigate = useNavigate()

  const [login, setLogin] = useState(false)
  const [user, SetUser] = useState(undefined)

  const logout = () => {
    doLogout(() => {
      //logged out

      setLogin(false)
      navigate("/")
    })
  }

  useEffect(() => {

    setLogin(isloggedin())
    SetUser(getCurrentUserDetail())

  }, [login])

  return (
    <div>
      <Navbar
        color="dark"
        dark
        expand="md"
        fixed=''
        className='px-4'
      >
        <NavbarBrand tag={ReactLink} to="/">Bloggerly</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to="/">New Feed</NavLink>
            </NavItem>

            <NavItem>
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>

            <NavItem>
              <NavLink tag={ReactLink} to="/services">Services</NavLink>
            </NavItem>


            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                More
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem>Contact us</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>LinkedIn</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Github</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>

          <Nav navbar>

            {
              login && (
                <>

                  <NavItem>
                    <NavLink tag={ReactLink} to="/user/profile-info">
                      My Profile 
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink tag={ReactLink} to="/user/dashboard">
                      {user.username}
                    </NavLink>
                  </NavItem>
                  
                  <NavItem>
                    <NavLink onClick={logout}>
                      Logout
                    </NavLink>
                  </NavItem>
                </>

              )
            }

            {
              !login && (

                <>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/login">Login</NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
                  </NavItem>

                </>

              )
            }

          </Nav>

        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;