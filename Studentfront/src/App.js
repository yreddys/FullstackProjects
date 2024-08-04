// src/App.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import ViewStudents from './components/ViewStudents';
import AddStudent from './components/AddStudent';
import Header from './components/Header';
import Footer from './components/Footer';

const App = () => {
    return (
        <div style={{ paddingBottom: '50px' }}> 
            <Router>
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/view-students" element={<ViewStudents />} />
                    <Route path="/add-student" element={<AddStudent />} />
                </Routes>
                <Footer />
            </Router>
        </div>
    );
}

export default App;
