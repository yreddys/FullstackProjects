// src/components/Header.js

import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header style={headerStyle}>
            <nav>
                <ul style={navListStyle}>
                    <li style={navItemStyle}><Link to="/" style={linkStyle}>Home</Link></li>
                    <li style={navItemStyle}><Link to="/View-Courses" style={linkStyle}>View Courses</Link></li>
                     <li style={navItemStyle}><Link to="/AddCourse" style={linkStyle}>AddCourse</Link></li>
                    
                </ul>
            </nav>
        </header>
    );
}

const headerStyle = {
    position: 'fixed', // Fixes the header at the top of the viewport
    top: '0',
    left: '0',
    width: '100%',
    backgroundColor: '#333',
    color: 'white',
    padding: '10px 0',
    textAlign: 'center',
    zIndex: '1000' // Ensures the header is above other content
};

const navListStyle = {
    listStyleType: 'none',
    margin: 0,
    padding: 0,
    display: 'flex',
    justifyContent: 'center',
};

const navItemStyle = {
    margin: '0 15px',
};

const linkStyle = {
    color: 'white',
    textDecoration: 'none',
};

export default Header;