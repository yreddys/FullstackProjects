// src/components/Home.js

import React from 'react';

const Home = () => {
    return (
        <div style={containerStyle}>
            <h1>Welcome to the Course Management System</h1>
            <p>Thanks!.</p>
        </div>
    );
};

const containerStyle = {
    maxWidth: '800px',
    margin: 'auto',
    padding: '20px',
    textAlign: 'center',
};

export default Home;
