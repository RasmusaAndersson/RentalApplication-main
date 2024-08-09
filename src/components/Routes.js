import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from '../UI-related/Home';
import About from '../UI-related/About';
import Contact from '../UI-related/Contact';
import RentForm from './RentForm';

const Routes = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/rent" element={<RentForm />} />
            </Routes>
        </Router>
    );
};

export default Routes;
