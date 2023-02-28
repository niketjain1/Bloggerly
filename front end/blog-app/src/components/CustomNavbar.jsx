import { NavLink as ReactLink } from 'react-router-dom';

import React, { useState } from 'react';
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
  NavbarText,
} from 'reactstrap';

const CustomNavbar = () => {

  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  return (
    <div>
      <Navbar 
        color="dark"
        dark
        expand="md"
        fixed=''
      >
        <NavbarBrand tag={ReactLink} to="/">My Blogs</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/login">Login</NavLink>
            </NavItem>
            <NavItem> 
              <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                More
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem tag={ReactLink} to="/services">Services</DropdownItem>
                <DropdownItem>Contact us</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>LinkedIn</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <NavbarText>Bloggerly</NavbarText>
        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;