import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Footer from './components/Footer';
import Header from './components/Header';
import AddCourse from './components/AddCourse';
import ViewCourses from './components/ViewCourses'; 
import Home from './components/Home';
import EnrollForm from './components/EnrollForm'; 

const App = () => {
  return (
    <div style={{ paddingBottom: '50px' }}> 
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/View-Courses" element={<ViewCourses />} />
          <Route path="/AddCourse" element={<AddCourse />} />
          <Route path="/EnrollForm" element={<EnrollForm />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
