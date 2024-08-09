import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RentForm from './components/RentForm';
import AdminOverview from './components/AdminOverview';
import Home from './UI-related/Home';
import About from './UI-related/About';
import Contact from './UI-related/Contact';
import Header from './UI-related/Header';
import Footer from './UI-related/Footer';

const App = () => {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/rent" element={<RentForm />} />
                <Route path="/admin" element={<AdminOverview />} />
            </Routes>
            <Footer />
        </Router>
    );
};

export default App;