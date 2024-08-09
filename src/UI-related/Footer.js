import React from 'react';
import '../css/Footer.css';

const Footer = () => {
    const year = new Date().getFullYear();

    return (
        <footer className="footer">
            &copy; {year} Andersson's Rental
        </footer>
    );
};

export default Footer;