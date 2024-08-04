// src/components/Header.js

import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header style={headerStyle}>
            <nav>
                <ul style={navListStyle}>
                    <li style={navItemStyle}><Link to="/" style={linkStyle}>Home</Link></li>
                    <li style={navItemStyle}><Link to="/view-students" style={linkStyle}>View Students</Link></li>
                    <li style={navItemStyle}><Link to="/add-student" style={linkStyle}>Add Student</Link></li>
                </ul>
            </nav>
        </header>
    );
}

const headerStyle = {
    backgroundColor: '#333',
    color: 'white',
    padding: '10px 0',
    textAlign: 'center',
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
